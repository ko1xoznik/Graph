package example.com;

import java.util.*;

//Интерфейс для неориентированного графа
public interface Graph {

    //Количество вершин
    int vertexCount();

    //Количество ребер
    int edgeCount();

    //Добавить ребро
    void addAdge(int var1, int var2);

    //Добавить вершину
    void addVertex();

    //Удалить ребро
    void removeAdge(int var1, int var2);

    //Рандомный граф
    void randomGraph(int var1, int var2);

    Iterable<Integer> adjacencies(int var1);

    //Есть ли связь между вершинами v1 и v2
    boolean isAdj(int v1, int v2);

    //Отрисовка в консоли в виде таблицы (если False - связи между вершинами нет, иначе связь есть)
    boolean[][] getTable();

    //Обход в ширину
    //Статичный, так относится к классу графа, а не к экземпляру
    static List<Integer> bfs(Graph graph, int from) {
        boolean[] visited = new boolean[graph.vertexCount()];
        List<Integer> answer = new LinkedList<>();
        Queue<Integer> queueWork = new LinkedList<>();
        queueWork.add(from);
        visited[from] = true;

        while(queueWork.size() > 0) {
            Integer curr = queueWork.remove();
            answer.add(curr);

            for (Integer v : graph.adjacencies(curr)) {
                if (!visited[v]) {
                    queueWork.add(v);
                    visited[v] = true;
                }
            }
        }

        return answer;
    }

    //Обход в длину
    //Статичный, так относится к классу графа, а не к экземпляру
    static List<Integer> dfs(Graph graph, int from) {
        boolean[] visited = new boolean[graph.vertexCount()];
        List<Integer> answer = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(from);
        visited[from] = true;
        answer.add(from);

        while(!stack.empty()) {
            Integer curr = stack.pop();
            if (!answer.contains(curr)) {
                answer.add(curr);
            }

            for (Integer v : graph.adjacencies(curr)) {
                if (!visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }

        return answer;
    }
}
