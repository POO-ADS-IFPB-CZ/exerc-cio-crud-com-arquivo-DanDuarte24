import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDAO {
    private Set<Pessoa> pessoas;
    private final String fileName = "pessoas.dat";

    public PessoaDAO() {
        pessoas = new HashSet<>();
        loadPessoas();
    }

    @SuppressWarnings("unchecked")
    private void loadPessoas() {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                pessoas = (Set<Pessoa>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                pessoas = new HashSet<>();
            }
        }
    }

    public void savePessoas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addPessoa(Pessoa pessoa) {
        boolean added = pessoas.add(pessoa);
        if (added) {
            savePessoas();
        }
        return added;
    }

    public Set<Pessoa> getPessoas() {
        return new HashSet<>(pessoas);
    }

    public boolean deletePessoaByEmail(String email) {
        boolean removed = pessoas.removeIf(p -> p.getEmail().equals(email));
        if (removed) {
            savePessoas();
        }
        return removed;
    }
}
