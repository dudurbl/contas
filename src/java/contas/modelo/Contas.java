
package contas.modelo;

import java.util.Date;

public class Contas {
    
    int idconta,idusuario;
    String descricao;
    double valor;
    Date data_pagamento,data_vencimento;

    public Contas(int idconta, String descricao, double valor, Date data_pagamento, Date data_vencimento) {
        this.idconta = idconta;
        this.descricao = descricao;
        this.valor = valor;
        this.data_pagamento = data_pagamento;
        this.data_vencimento = data_vencimento;
    }

    public Contas() {
    }
    
    
    public int getIdconta() {
        return idconta;
    }

    public void setIdconta(int idconta) {
        this.idconta = idconta;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }


}
