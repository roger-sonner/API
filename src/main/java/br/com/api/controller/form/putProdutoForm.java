package br.com.api.controller.form;

import br.com.api.model.Produto;
import br.com.api.repository.ProdutoRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class putProdutoForm {
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 200)
    private String descricao;
    @Digits(integer=12, fraction=2) @DecimalMin("0.01")
    private BigDecimal valorUnitario;

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {

        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {

        this.valorUnitario = valorUnitario;
    }

    public Produto atualizar(Long id, ProdutoRepository repository) {
        Produto produto = repository.getReferenceById(id);
        produto.setDescricao(this.descricao);
        produto.setValorUnitario(this.valorUnitario);
        return produto;
    }

}
