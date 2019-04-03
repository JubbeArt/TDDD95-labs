import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 03/04/19
 */

public class unfairplay {
    static Kattio io = new Kattio(System.in, System.out);

    /**
     * General solution is:
     *      make it so your team wins all your own matches
     *
     *      then construct a graph doing the following:
     *          - add a start node
     *          - add all matches as nodes
     *          - add all teams as nodes
     *          - add a end node
     *
     *      make edges:
     *          - start to all matches (capacity of 2 to each)
     *          - matches to respective team of that match
     *          - each team should have edge to the sink
     *          - limit the team-sink edge to not have more points than your own team
     *
     *      if the flow problem is solvable (so that all matches get played) then the matches are riggable
     *
     *      get the results by looking at the flow of the match-team edges
     *
     */

    static final int sourceIndex = 0;
    static final int sinkIndex = 1;
    static int myTeamIndex;
    static int numberOfMatches;
    static int numberOfTeams;
    static int totalFlowForSolution;

    public static void main(String[] args) {
        while (true) {
            numberOfTeams = io.getInt();

            if (numberOfTeams == -1) {
                break;
            }

            numberOfMatches = io.getInt();

            // get all team points
            int[] teamPoints = new int[numberOfTeams];

            for (int i = 0; i < numberOfTeams; i++) {
                teamPoints[i] = io.getInt();
            }

            // edge case if no matches, just check the current standing and see if we're on top
            if(numberOfMatches == 0) {
                boolean isOnTop = true;

                for(int i = 0; i < numberOfTeams - 1; i++) {
                    if(teamPoints[i] >= teamPoints[teamPoints.length - 1]) {
                        isOnTop = false;
                    }
                }

                if(isOnTop) {
                    io.println();
                } else {
                    io.println("NO");
                }

                continue;
            }


            // init arrays for EdmondsKarp
            ArrayList<Edge> allEdges = new ArrayList<>();
            List<Match> matches = new ArrayList<>(numberOfMatches);
            int numOfNodes = 2 + numberOfTeams + numberOfMatches; // source + sink + teams + matches
            myTeamIndex = numberOfTeams - 1;
            totalFlowForSolution = 0;

            // get all matches and add edges from source to matches
            for(int i = 0; i < numberOfMatches; i++) {
                Match match = new Match(io.getInt() - 1, io.getInt() - 1);
                matches.add(i, match);

                // rig it so that we always win our matches
                if(match.team1 == myTeamIndex || match.team2 == myTeamIndex) {
                    teamPoints[myTeamIndex] += 2;

                    // add dummy match to graph (not needed but easier with current code)
                    allEdges.add(new Edge(sourceIndex, getMatchIndex(i),0));
                } else {
                    totalFlowForSolution += 2;
                    allEdges.add(new Edge(sourceIndex, getMatchIndex(i), 2));
                }
            }

            List<Edge> matchEdges = new ArrayList<>(); // save these edges for output later on

            // add edges from matches to corresponding teams
            for(int i = 0; i < numberOfMatches; i++) {
                Edge matchTeam1 = new Edge(getMatchIndex(i), getTeamIndex(matches.get(i).team1), Integer.MAX_VALUE);
                Edge matchTeam2 = new Edge(getMatchIndex(i), getTeamIndex(matches.get(i).team2), Integer.MAX_VALUE);

                allEdges.add(matchTeam1);
                allEdges.add(matchTeam2);
                matchEdges.add(matchTeam1);
            }

            // add edges from teams to sink
            for(int i = 0; i < numberOfTeams; i++) {
                // no other team can have more points then us
                Edge edge = new Edge(getTeamIndex(i), sinkIndex, teamPoints[myTeamIndex] - 1 - teamPoints[i]);
                allEdges.add(edge);
            }

            EdmondsKarp ek = new EdmondsKarp(allEdges, numOfNodes, sourceIndex, sinkIndex);
            boolean solvedMaxFlow = ek.edmondsKarp();

            if(solvedMaxFlow) {
                for(int i = 0; i < numberOfMatches; i++) {
                    Match match = matches.get(i);

                    if(match.team1 == myTeamIndex) {
                        io.print(0 + " ");
                    } else if(match.team2 == myTeamIndex) {
                        io.print(2 + " ");
                    } else {
                        long team1Flow = matchEdges.get(i).flow;

                        if(team1Flow == 2) {
                            io.print(0 + " ");
                        } else if(team1Flow == 1) {
                            io.print(1 + " ");
                        } else {
                            io.print(2 + " ");
                        }
                    }
                }
                io.println();
            } else {
                io.println("NO");
            }
        }

        io.close();
    }

    static int getMatchIndex(int match) {
        return 2 + match; // first two are source and sink
    }

    static int getTeamIndex(int team) {
        return 2 + numberOfMatches + team;
    }

    static class EdmondsKarp {
        ArrayList<Edge> allEdges;
        ArrayList<Edge>[] edgeLookup;
        int numOfNodes;
        long totalFlow;
        int source;
        int sink;

        EdmondsKarp(ArrayList<Edge> allEdges, int numOfNodes, int source, int sink) {
            this.numOfNodes = numOfNodes;
            this.allEdges = allEdges;
            this.totalFlow = 0;
            this.source = source;
            this.sink = sink;

            this.edgeLookup = new ArrayList[numOfNodes];
            for(int i = 0; i < numOfNodes; i++) {
                edgeLookup[i] = new ArrayList<>();
            }

            int size = allEdges.size();

            for(int i = 0; i < size; i++) {
                Edge edge = allEdges.get(i);

                Edge reverse = new Edge(edge.end, edge.start, 0);
                edge.reverse = reverse;
                reverse.reverse = edge;
                reverse.flow = -edge.flow;

                edgeLookup[edge.start].add(edge);
                edgeLookup[edge.end].add(reverse);
                allEdges.add(reverse);
            }
        }

        private boolean edmondsKarp() {
            while(true) {
                // Do BFS to find the shortest path from source to sink
                Queue<Integer> unsearchedNodes = new ArrayDeque<>();
                unsearchedNodes.add(source);

                Edge[] parents  = new Edge[numOfNodes];

                while(!unsearchedNodes.isEmpty()) {
                    int current = unsearchedNodes.poll();

                    for(Edge edge : edgeLookup[current]) {
                        // new available path found (that can take more items)
                        if(parents[edge.end] == null && edge.end != source && edge.capacity > edge.flow) {
                            parents[edge.end] = edge;
                            unsearchedNodes.add(edge.end);
                        }
                    }
                }

                // no valid path found by the BFS
                if(parents[sink] == null) {
                    break;
                }

                long newFlow = Integer.MAX_VALUE;

                // check the max amount of flow possible for the found path
                for(Edge edge = parents[sink]; edge != null; edge = parents[edge.start]) {
                    newFlow = Math.min(newFlow, edge.capacity - edge.flow);
                }

                // update node info
                for(Edge edge = parents[sink]; edge != null; edge = parents[edge.start]) {
                    edge.flow += newFlow;
                    edge.reverse.flow -= newFlow;

                }

                totalFlow += newFlow;
            }
            return totalFlow == totalFlowForSolution;

        }
    }

    static class Match {
        int team1;
        int team2;

        Match(int team1, int team2) {
            this.team1 = team1;
            this.team2 = team2;
        }
    }

    static class Edge {
        int start;
        int end;
        long capacity;
        long flow;
        Edge reverse;

        Edge(int start, int end, long capacity) {
            this.start = start;
            this.end = end;
            this.capacity = capacity;
            this.flow = 0;
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }
    }


}
