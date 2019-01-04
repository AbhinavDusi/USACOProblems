import sys;
fin = open("/Users/abhinavdusi/Desktop/3.in", "r+");
fout = open("/Users/abhinavdusi/Desktop/3.out", "w+");
line = fin.readline();
def bellmanford(edges, start, end, n):
    distances, nEdges, predecessors, nie = [], [], [], [];
    for i in range (0, n):
        distances.append(sys.maxint);
        predecessors.append(0);
    distances[start] = 0;
    for i in range(0, n - 1):
        for j in edges:
            for edge in edges[j]:
                e, w = edge[0], edge[1];
                if distances[j] != sys.maxint and distances[e] > distances[j] + w:
                    distances[e] = distances[j] + w;
                    predecessors[e] = j;
    for i in edges:
        for edge in edges[i]:
            e, w = edge[0], edge[1];
            if distances[e] > distances[i] + w:
                nEdges.append([i, e]);
    for i in range(0, n):
        curr = predecessors[i];
        while curr != 0:
            if [predecessors[curr], curr] in nEdges:
                nie.append(i);
                break;
            curr = predecessors[curr];
    for i in endList:
        if i in nie:
            fout.write("-Infinity\n");
        else:
            if distances[i] != sys.maxint:
                fout.write(str(distances[i]) + "\n");
            else:
                fout.write("Impossible\n");
while line != "0 0 0 0":
    data = map(int, line.split(" "));
    n, e, q, s = data[0], data[1], data[2], data[3];
    edges = {};
    endList = [];
    for i in range(0, n):
        edges[i] = [];
    for i in range(0, e):
        data = map(int, fin.readline().split(" "));
        edges[data[0]].append([data[1], data[2]]);
    for i in range(0, q):
        endList.append(int(fin.readline()));
    bellmanford(edges, s, endList, n);
    fout.write("\n");
    line = fin.readline();
fin.close();
fout.close();
