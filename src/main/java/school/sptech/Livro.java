package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Livro extends ItemBiblioteca{

    private String autor;

    public Livro(String titulo, String autor) {
        super(titulo);

        if (autor == null || autor.isBlank()){
            throw new DadoInvalidoException("Autor inválido");
        }
        this.autor = autor;
    }

    @Override
    public Integer getPrazoEmprestimoDias() {
        return 14;
    }








    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}