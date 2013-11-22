package test;

import main.Graph;
import main.Vertex;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GraphTest {
    private Vertex lille = new Vertex("Lille");
    private Vertex paris = new Vertex("Paris");
    private Vertex reims = new Vertex("Reims");
    private Vertex nancy = new Vertex("Nancy");
    private Vertex lyon = new Vertex("Lyon");
    private Vertex marseille = new Vertex("Marseille");
    private Vertex lemans = new Vertex("Le Mans");
    private Vertex nantes = new Vertex("Nantes");
    private Vertex bordeaux = new Vertex("Bordeaux");
    private Vertex toulouse = new Vertex("Toulouse");
    private Vertex clermont = new Vertex("Clermont Ferrant");
    private Vertex montpellier = new Vertex("Montpellier");

    @Before
    public void setup() {
        lille.connectTo(reims, 206);
        lille.connectTo(paris, 222);
        lille.connectTo(nancy, 418);

        reims.connectTo(paris, 144);
        reims.connectTo(nancy, 245);
        reims.connectTo(lyon, 489);

        paris.connectTo(lyon, 465);
        paris.connectTo(lemans, 208);
        paris.connectTo(clermont, 423);

        lyon.connectTo(clermont, 166);
        lyon.connectTo(marseille, 313);
        lyon.connectTo(montpellier, 304);

        lemans.connectTo(nantes, 189);
        lemans.connectTo(bordeaux, 443);

        nantes.connectTo(bordeaux, 347);

        bordeaux.connectTo(toulouse, 243);

        toulouse.connectTo(montpellier, 245);

        montpellier.connectTo(marseille, 169);
        montpellier.connectTo(toulouse, 245);

        marseille.connectTo(montpellier, 169);

        clermont.connectTo(lyon, 166);
        clermont.connectTo(montpellier, 333);
        clermont.connectTo(marseille, 474);
    }

    public Graph allCities() {
        return new Graph(lille, paris, lemans, reims, marseille, lyon, clermont, montpellier, nantes, bordeaux, toulouse);
    }

    @Test
    public void getDistanceForTwoAdjacentVertices() {
        Graph graph = new Graph(paris, lyon);
        assertEquals(graph.getDistance("Paris", "Lyon"), 465);

    }

    @Test
    public void getDistanceForTwoAdjacentVerticesWithMoreVertex(){
        Graph graph1 = new Graph(paris, lyon, lemans, clermont);
        assertEquals(graph1.getDistance("Paris", "Clermont Ferrant"), 423);
    }

    @Test
    public void getDistanceWithAnIntermediateVertex(){
        Graph graph2 = new Graph(lille, reims, lyon, paris);
        assertEquals(graph2.getDistance("Lille", "Lyon"), 687);
    }

    @Test
    public void getDistanceWithTwoIntermediateVertices(){
        Graph graph3 = new Graph(lille, paris, lemans, nantes);
        assertEquals(graph3.getDistance("Lille", "Nantes"), 619);
    }

    @Test
    public void getDistanceWithSeveralPossibilities() {
        assertEquals(allCities().getDistance("Lille", "Montpellier"), 978);
    }

    @Test
    public void getDistanceWithNoVertexInGraph() {
        Graph graph5 = new Graph();
        assertEquals(graph5.getDistance("Paris", "Lille"),0);
    }

    @Test
    public void getDistanceIfCityNoExist() {
        assertEquals(allCities().getDistance("Dijon", "Marseille"),0);
    }

    @Test
    public void getDistanceIfNoPathAvailable() {
        assertEquals(allCities().getDistance("Paris","Lille"),0);
    }

    @Test
    public void getDistanceIfNoEdgeInGraph() {
        Graph graph8 = new Graph(paris, lyon, toulouse);
        assertEquals(graph8.getDistance("Paris", "Toulouse"),0);
    }



}
