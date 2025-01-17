package posjavamavenhibernate;

import java.util.List;

import org.junit.jupiter.api.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa(); 
		
		pessoa.setIdade(45);
		pessoa.setLogin("tesfghfghte");
		pessoa.setNome("Paulo");
		pessoa.setSenha("sdsfhshds123");
		pessoa.setSobrenome("Pdhfahadhalhares");
		pessoa.setEmail("ahadhearhfedbtaurmimimi@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa(); 
		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		
		pessoa.setIdade(99);
		pessoa.setNome("Clarice atualizado Hibernate");
		pessoa.setSenha("admin");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
		
		
	}
	
	
	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------------------------------------------------------");
		}
		
	}
	
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Weslley 2'").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by id ")
				.setMaxResults(3)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "Paulo")
				.setParameter("sobrenome", "Palhares")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager().
				createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println("Soma de todas as idades é --> " + somaIdade);
	}

	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}
	
	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Paulo")
				.getResultList();
		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}
	
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(11L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("1837422425");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
		
	}
	
	@Test
	public void testeConsultaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(11L, UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("====================================================================");
		}
		
	}
}
