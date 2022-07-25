package br.com.api.controller.form;

import br.com.api.model.Produto;
import com.sun.istack.NotNull;


import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoForm {
    @Min(1)
    private Long codigo;
    @NotNull  @NotEmpty @Size(min = 5, max = 200)
    private String descricao;
    @Digits(integer=12, fraction=2) @DecimalMin("0.01")
    private BigDecimal valorUnitario;

    public Long getCodigo() {

        return codigo;
    }

    public void setCodigo(Long codigo) {

        this.codigo = codigo;
    }

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

    public Produto converterProduto() {
        return new Produto(codigo, descricao, valorUnitario);
    }
}
