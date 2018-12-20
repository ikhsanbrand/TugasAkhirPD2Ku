package FormTugasAkhir;


public class Tri {

    public static Tree t;

    public static void main(String[] args) {

//        t = new Tree(new TreeNode("Surabaya"));
        t.root.add_child(new TreeNode(""), 0);
        t.root.add_child(new TreeNode(""), 0);
        t.root.add_child(new TreeNode(""), 0);
        t.root.add_child(new TreeNode(""), 0);
        t.root.add_child(new TreeNode(""), 0);
        int panjang = t.root.children.size();
        String[] titik = new String[panjang];
        double[] KM = new double[panjang];
        for (int i = 0; i < t.root.children.size(); i++) {
            titik[i] = t.root.children.get(i).data;
            KM[i] = t.root.children.get(i).distance;
        }
    }
}
