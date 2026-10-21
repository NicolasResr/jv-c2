package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.ItemIndisponivelException;

public class ItemBiblioteca {

    private String titulo;
    private Usuario emprestadoPara;

    public ItemBiblioteca(String titulo) {
        if (titulo == null || titulo.isBlank()){
            throw new DadoInvalidoException("titulo inválido");
        }

        this.titulo = titulo;
    }

    //Metodo emprestar deve declarar que lança = throws ...Exception
    public void emprestar(Usuario usuario) throws ItemIndisponivelException {
        if (usuario == null){
            throw new DadoInvalidoException("usuario inválido");
        }

        if (emprestadoPara != null ){
            throw new ItemIndisponivelException("item indisponivel");
        }

        emprestadoPara = usuario;
    }

    public void devolver(){
        emprestadoPara = null;
    }

    public Integer getPrazoEmprestimoDias() {
    return 7;
    }






























    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getEmprestadoPara() {
        return emprestadoPara;
    }

    public void setEmprestadoPara(Usuario emprestadoPara) {
        this.emprestadoPara = emprestadoPara;
    }
}