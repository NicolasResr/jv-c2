package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Usuario {

//    public	+	Público
//    private	-	Privado
//    protected	#	Protegido
//    default	~	Acesso ao pacote

    private String nome;

// lançar a exception no construtor

    public Usuario(String nome) {
        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException("Nome inválido");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }




}