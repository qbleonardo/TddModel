# TddModel
Atividade para aula de TTD com JUnit em Spring, o objetivo é realizar o teste unitário, porém construindo algumas camadas de aplicação.

<h3>Spring TDD com JUnit (Model)</h3>
<hr>
<h2>Testando a Model @NotEmpty</h2>
Antes de fazer o teste unitário vamos construir algumas camadas da aplicação
<hr>
<h1>Model/Entity</h1>
1. Crie um pacote e o nomeie como Model ou Entity, em seguida cole o código abaixo;

    package integracao.bancodedados.model;
    
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.validation.constraints.NotEmpty;
    
    @Entity
    public class ContatoModel {
    
    	//ATRIBUTOS
    	@Id
    	@GeneratedValue(strategy=GenerationType.IDENTITY)
    	private Long id;
    	
    	@NotEmpty(message="O DDD deve ser preenchido")
    	private String ddd;
    	
    	@NotEmpty(message="O Telefone deve ser preenchido")
    	private String telefone;
    	
    	@NotEmpty(message="O Nome deve ser preenchido")
    	private String nome;
    
    	//CONTRUCTORS
    	public ContatoModel(){
    	}
    	
    	public ContatoModel(String nome, String ddd, String telefone) {
    		this.nome = nome;
    		this.ddd = ddd;
    		this.telefone = telefone;
    	}
    	
    	//GETTERS AND SETTERS
    	public Long getId() {
    		return id;
    	}
    
    	public void setId(Long id) {
    		this.id = id;
    	}
    
    	public String getDdd() {
    		return ddd;
    	}
    
    	public void setDdd(String ddd) {
    		this.ddd = ddd;
    	}
    
    	public String getTelefone() {
    		return telefone;
    	}
    
    	public void setTelefone(String telefone) {
    		this.telefone = telefone;
    	}
    
    	public String getNome() {
    		return nome;
    	}
    
    	public void setNome(String nome) {
    		this.nome = nome;
    	}
    } 



<h1>Crie uma Interface de repository para usar os recursos do JPAHibernat</h1>

2. Crie um pacote e o nomeio como repository e insira o codigo abaixo;

package integracao.bancodedados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import integracao.bancodedados.model.ContatoModel;

public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {

}

Pronto! agora ja temos um projeto onde possamos implementar uma classe de teste.

<h1>Implementando os testes unitários.</h1>

Nesta seção iremos realizar testes unitários nas nossas anotações de validações inseridas na nossa model/entity.

3. Em src/test/java Crie um pacote e o nomeie como contato, em seguida insira o código abaixo;

package integracao.bancodedados.contatos;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import integracao.bancodedados.model.ContatoModel;
import integracao.bancodedados.repository.ContatoRepository;


//@RunWith rodará com o spring runner,
//que suporta testes de integração de interfaces (testa se a 
//repository está funcionando)

@RunWith(SpringRunner.class)
@DataJpaTest //melhor testador de JPA
public class ContatosRepositoryIntegrationTest {

	private ContatoModel contato;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Before
	public void start() {
		contato = new ContatoModel("Gabriel", "011y", "9xxxxxxx9");
	}
	
	//regra, não aceita exceções, é a maneira de especificar
	//que a execução de um teste lançará uma exceção
	//Verifica se o @NotEmpty está funcionando
	@Rule
	public ExpectedException esperadaExcecao = ExpectedException.none();
	
	@Test
	public void salvarComTelNulo() throws Exception {
		esperadaExcecao.expect(ConstraintViolationException.class);
		esperadaExcecao.expectMessage("O Telefone deve ser preenchido");
		
		contato.setTelefone(null);
		contatoRepository.save(contato);
	}
	
	@Test
	public void salvarComDddNulo() throws Exception {
		esperadaExcecao.expect(ConstraintViolationException.class);
		esperadaExcecao.expectMessage("O DDD deve ser preenchido");
		
		contato.setDdd(null);
		contatoRepository.save(contato);
	}
	
	@Test
	public void salvarComNomeNulo() throws Exception {
		esperadaExcecao.expect(ConstraintViolationException.class);
		esperadaExcecao.expectMessage("O Nome deve ser preenchido");
		
		contato.setNome(null);
		contatoRepository.save(contato);
	}
	
}

<img src = "https://camo.githubusercontent.com/8146472e535b637d459184b524e9b81bfb7ad479e0b52bdf508b029c9a8e1739/68747470733a2f2f692e696d6775722e636f6d2f4a434c496d736c2e706e67">
