package example.com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class AdjMatrixGraphTest {

    @Test
    void vertexCount() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(5);
        Assertions.assertEquals(5, adj.vertexCount());
        Assertions.assertEquals(5, diadj.vertexCount());
    }

    @Test
    void edgeCount() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(5);
        Assertions.assertEquals(0, adj.edgeCount());
        Assertions.assertEquals(0, diadj.edgeCount());
    }

    @Test
    void addAdge() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(5);
        adj.addAdge(1, 2);
        diadj.addAdge(1, 2);
        Assertions.assertTrue(adj.getTable()[1][2]);
        Assertions.assertTrue(adj.getTable()[2][1]);
        Assertions.assertTrue(diadj.getTable()[1][2]);
    }

    @Test
    void addVertex() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(5);
        adj.addVertex();
        diadj.addVertex();
        Assertions.assertEquals(6, adj.vertexCount());
        Assertions.assertEquals(6, adj.vertexCount());
    }

    @Test
    void removeAdge() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(5);
        adj.addAdge(1, 2);
        adj.removeAdge(1, 2);
        diadj.addAdge(1, 2);
        diadj.removeAdge(1, 2);
        Assertions.assertFalse(adj.getTable()[1][2]);
        Assertions.assertFalse(adj.getTable()[2][1]);
        Assertions.assertFalse(diadj.getTable()[1][2]);
    }

    @Test
    void isAdj() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(5);
        adj.addAdge(1, 2);
        diadj.addAdge(1, 2);
        Assertions.assertEquals(adj.getTable()[1][2], adj.isAdj(1, 2));
        Assertions.assertEquals(adj.getTable()[2][1], adj.isAdj(2, 1));
        Assertions.assertEquals(diadj.getTable()[1][2], diadj.isAdj(1, 2));
        Assertions.assertEquals(diadj.getTable()[2][1], diadj.isAdj(2, 1));
    }

    @Test
    void dfs() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(8);
        adj.addAdge(0, 1);
        adj.addAdge(1, 2);
        adj.addAdge(0, 4);
        adj.addAdge(2, 3);
        adj.addAdge(2, 5);
        adj.addAdge(4, 6);
        adj.addAdge(4, 7);
        diadj.addAdge(0, 1);
        diadj.addAdge(1, 2);
        diadj.addAdge(0, 4);
        diadj.addAdge(2, 3);
        diadj.addAdge(2, 5);
        diadj.addAdge(4, 6);
        diadj.addAdge(4, 7);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(0, 4, 7, 6, 1, 2, 5, 3)), Graph.dfs(adj, 0));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(0, 4, 7, 6, 1, 2, 5, 3)), Graph.dfs(diadj, 0));
    }

    @Test
    void bfs() {
        AdjMatrixDigraph diadj = new AdjMatrixDigraph(5);
        AdjMatrixGraph adj = new AdjMatrixGraph(7);
        adj.addAdge(0, 1);
        adj.addAdge(1, 2);
        adj.addAdge(0, 4);
        adj.addAdge(4, 3);
        adj.addAdge(4, 5);
        adj.addAdge(5, 6);
        diadj.addAdge(0, 1);
        diadj.addAdge(1, 2);
        diadj.addAdge(0, 4);
        diadj.addAdge(4, 3);
        diadj.addAdge(4, 5);
        diadj.addAdge(5, 6);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(0, 1, 4, 2, 3, 5, 6)), Graph.bfs(adj, 0));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(0, 1, 4, 2, 3, 5, 6)), Graph.bfs(diadj, 0));
    }
}