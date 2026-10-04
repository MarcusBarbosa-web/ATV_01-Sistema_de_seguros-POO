package br.edu.cs.poo.ac.seguro.entidades;

/*
 * Implementar um enum com as seguintes constantes:
 * 
 * 	COLISAO(1,"Colis\u00e3o"),
	INCENDIO(2,"Inc\u00eandio"),
	FURTO(3, "Furto"),
	ENCHENTE(4, "Enchente"),
	DEPREDACAO(5, "Depreda\u00e7\u00e3o");
 * 
 * O enum deve ter construtor privado, m\u00e9todos get p\u00fablicos para os atributos codigo e nome,
 * e um m\u00e9todo p\u00fablico e est\u00e1tico TipoSinistro getTipoSinistro(int codigo), que 
 * retorna o tipo de sinistro correspondente ao c\u00f3digo recebido como par\u00e2metro
 */
public enum TipoSinistro {
	COLISAO(1,"Colis\u00e3o"),
	INCENDIO(2,"Inc\u00eandio"),
	FURTO(3, "Furto"),
	ENCHENTE(4, "Enchente"),
	DEPREDACAO(5, "Depreda\u00e7\u00e3o");
	
	private final int codigo;
	private final String nome;
	
	private TipoSinistro(int codigo, String nome) {
	    this.codigo = codigo;
	    this.nome = nome;
	}
	
	public int getCodigo() {
	    return codigo;
	}

	public String getNome() {
	    return nome;
	}

	public static TipoSinistro getTipoSinistro(int codigo) {
	    for (TipoSinistro tipo : TipoSinistro.values()) {
	        if (tipo.getCodigo() == codigo) {
	            return tipo;
	        }
	    }
	    return null;
	}
}