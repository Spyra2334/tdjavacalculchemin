package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Graph {

    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to) {

        Vertex destination = null;
        Vertex departure = null;
        List<Vertex> temporaryList = new ArrayList<Vertex>();
        Vertex currentVertex = null;

        for (int count = 0; count < vertices.size(); count++)
        {
            if (((vertices.get(count)).getName()).equals(from))
            {
                departure = vertices.get(count);
            }
            if (((vertices.get(count)).getName()).equals(to))
            {
                destination = vertices.get(count);
            }
            vertices.get(count).setDist(-1);
            vertices.get(count).setVisited(false);
        }

        if (!(destination == null) && !(departure == null))
        {
            departure.setDist(0);
            temporaryList.add(departure);

            while (temporaryList.size() != 0)
            {
                int smallest = temporaryList.get(0).getDist();
                for (Vertex vertexInTemporaryList : temporaryList) {

                    if ((vertexInTemporaryList.getDist()>=0) && (vertexInTemporaryList.getDist() <= smallest) && (!vertexInTemporaryList.isVisited())) {
                        currentVertex = vertexInTemporaryList;
                    }
                }
                if (currentVertex.equals(destination)){
                    return currentVertex.getDist();
                }

                temporaryList.remove(currentVertex);
                currentVertex.setVisited(true);

                for (int count =0; count<currentVertex.getEdges().size(); count++)
                {
                    int alt = currentVertex.getDist() + currentVertex.getEdges().get(count).getDistance();
                    if (((alt < currentVertex.getEdges().get(count).getTarget().getDist()) || (currentVertex.getEdges().get(count).getTarget().getDist() == -1)) && (!currentVertex.getEdges().get(count).getTarget().isVisited()))
                    {
                        currentVertex.getEdges().get(count).getTarget().setDist(alt);
                        temporaryList.add(currentVertex.getEdges().get(count).getTarget());
                    }
                }

            }
            if (currentVertex.equals(destination)){
                return currentVertex.getDist();
            }
            else{
                return 0;
            }
        }

        else{
            return 0;
        }
    }
}
