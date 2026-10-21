package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.ItemIndisponivelException;
import school.sptech.exceptions.ItemNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<ItemBiblioteca> itens = new ArrayList<>();

    public Biblioteca(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadoInvalidoException("nome inválido");
        }

        this.nome = nome;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemBiblioteca item) throws DadoInvalidoException{
        if (item == null){
            throw new DadoInvalidoException("Autor inválido");
        }

    //Caso válido, adiciona na lista de itens
    itens.add(item);
    }

    public ItemBiblioteca buscarPorTitulo(String titulo) throws  ItemNaoEncontradoException{
        for (ItemBiblioteca iten : itens) {
            if (iten.getTitulo().equalsIgnoreCase(titulo)){
                return iten;
            }
        }
        throw new ItemNaoEncontradoException("Item não encontrado");

    }

    public void emprestarItem(String titulo, Usuario usuario) throws ItemNaoEncontradoException {
        if (titulo == null || usuario == null) {
            throw new DadoInvalidoException("Título ou usuário inválido");
        }

        ItemBiblioteca item = buscarPorTitulo(titulo); //Aqui ele Já lança o ItemNaoEncontradoException por causa da função a cima

        //Usei o try catch pois o ItemIndisponivelException é um checked, então para não propaga-lo utilizo o try catch
        try {
            item.emprestar(usuario);
        } catch (ItemIndisponivelException e) {
            System.out.println("Esse item já está emprestado para outro usuário.");
        }
    }

    public void devolverItem(String titulo) throws ItemNaoEncontradoException{
        if (titulo == null ){
            throw new DadoInvalidoException("Autor inválido");
        }

        ItemBiblioteca item = buscarPorTitulo(titulo);
        item.devolver();

    }

    public Boolean possuiItemDisponivel(String titulo) throws ItemNaoEncontradoException{
        if (titulo == null || titulo.isBlank()){
            throw new DadoInvalidoException("Autor inválido");
        }

        ItemBiblioteca itens = buscarPorTitulo(titulo);

        if (itens != null){
            if (itens.getEmprestadoPara() != null){
                return false;
            }
        }
        return true;
    }

    public List<ItemBiblioteca> listarDisponiveis(){
      List<ItemBiblioteca> listaDisponiveis = new ArrayList<>();

        for (ItemBiblioteca iten : itens) {
            if (iten.getEmprestadoPara() == null){
                listaDisponiveis.add(iten);
            }

        }
        return listaDisponiveis;
    }

    public Integer contarPorTipo(String tipo){
        if (tipo == null || tipo.isBlank() || (!tipo.equalsIgnoreCase("dvd") && !tipo.equalsIgnoreCase("Livro"))){
            throw new DadoInvalidoException("Tipo inválido");
        }
        int contador = 0;

        for (ItemBiblioteca iten : itens) {
            if (tipo.equalsIgnoreCase("DVD") && iten instanceof Dvd){
                contador ++;
            } else if (tipo.equalsIgnoreCase("Livro") && iten instanceof Livro){
                contador ++;
            }
        }
        return contador;

    }

    public Integer calcularPrazoMedioEmprestimo(){
        //Aqui eu procuro se não tem itens cadastrado puxando o valor declarado na linha 12
        if (itens == null || itens.isEmpty()) {
            return 0;
        }

        Integer prazoTotal = 0;
        for (ItemBiblioteca iten : itens) {
            prazoTotal += iten.getPrazoEmprestimoDias();
        }

        return prazoTotal / itens.size();
    }

    public Integer calcularPrazoMedioPorTipo(String tipo){
        if (tipo == null || tipo.isBlank() || (!tipo.equalsIgnoreCase("dvd") && !tipo.equalsIgnoreCase("Livro"))){
            throw new DadoInvalidoException("Tipo inválido");
        }

        if (itens == null || itens.isEmpty() || (!tipo.equalsIgnoreCase("dvd") && !tipo.equalsIgnoreCase("Livro"))) {
            return 0;
        }

        Integer quantidade = contarPorTipo(tipo);
        if (quantidade == 0){
            return 0;
        }

        Integer media = 0;
        for (ItemBiblioteca iten : itens) {
            if (tipo.equalsIgnoreCase("dvd") && iten instanceof Dvd){
                media += iten.getPrazoEmprestimoDias();
            }

            if ((tipo.equalsIgnoreCase("livro") && iten instanceof Livro)){
                media += iten.getPrazoEmprestimoDias();
            }
        }

        return media/quantidade;
    }

    public List<ItemBiblioteca> listarEmprestadosPara(Usuario usuario){
        if (usuario == null){
            throw new DadoInvalidoException("usuario inválido");
        }

        List<ItemBiblioteca> listaEmpreUser = new ArrayList<>();

        for (ItemBiblioteca iten : itens) {
            // comparar o atributo nome do Usuario, não só o objeto usuario, mas sim especificamente o nome dele por isso o uso de getNome em tudo
            if (iten.getEmprestadoPara() != null && // o iten.getEmprestadoPara() != null eu usei pois se um item n tiver emprestado ele tem que ser diferente de null
                    iten.getEmprestadoPara().getNome().equals(usuario.getNome())){
                listaEmpreUser.add(iten);
            }
        }

        return listaEmpreUser;
    }


    public void devolverTodosDoUsuario(Usuario usuario){
        if (usuario == null){
            throw new DadoInvalidoException("usuario inválido");
        }

        for (ItemBiblioteca iten : itens) {
            if (iten.getEmprestadoPara() != null && iten.getEmprestadoPara().getNome().equalsIgnoreCase(usuario.getNome())){
                iten.devolver();
            }
        }
    }


















































































    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemBiblioteca> getItens() {
        return itens;
    }

    public void setItens(List<ItemBiblioteca> itens) {
        this.itens = itens;
    }
}