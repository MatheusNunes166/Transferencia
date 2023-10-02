package com.Bancosesi.Transferencia.Service;

import com.Bancosesi.Transferencia.Model.M_Usuario;
import com.Bancosesi.Transferencia.Repository.R_Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {
    private static R_Usuario r_usuario;

    public S_Usuario(R_Usuario r_usuario) {
        this.r_usuario = r_usuario;
    }

    public static M_Usuario verificaLogin(String matricula, String senha) {

        matricula = S_Generico.limparNumero(matricula);
        if (S_Generico.textoEstaVazio(matricula)) {
            return null;
        }else if(S_Generico.textoEstaVazio(matricula)) {
            return null;
        }
        return r_usuario.findByMatriculaESenha(Long.parseLong(matricula),senha);
    }

    public static String cadastrarUsuario(String nome, String email, String cpf, String cep, String estado) {
        boolean podeSalvar = true;
        String mensagem = "";
        if (S_Generico.textoEstaVazio(nome)) {
            podeSalvar = false;
            mensagem += "O nome precisa ser preenchido!";
        }
        if (!S_Generico.validarEmail(email)) {
            podeSalvar = false;
            mensagem += "e-Mail inválido!";
        }
        if (S_Generico.textoEstaVazio(S_Generico.limparNumero((cpf)))) {
            podeSalvar = false;
            mensagem += "Cpf nao preenchido";
        }

        if (S_Generico.textoEstaVazio(S_Generico.limparNumero((cep)))) {
            podeSalvar = false;
            mensagem += "cep nao preenchido";
        }
        if (S_Generico.textoEstaVazio(S_Generico.limparNumero((estado)))) {
            podeSalvar = false;
            mensagem += "estado nao preenchido";
        }
        if (podeSalvar) {
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setEmail(email);
            m_usuario.setCpf(Long.parseLong(cpf));
            m_usuario.setCep(Long.parseLong(cep));
            m_usuario.setEstado_id(Long.parseLong(estado));
            m_usuario.setSenha(S_GeradorSenha.gerarSenha(5, 3, 2));


            try {
                r_usuario.save(m_usuario);
                mensagem += "Deu Bom";
            } catch (DataIntegrityViolationException e) {
                mensagem += "Deu Ruim";
            }
        }
        return mensagem;
    }
}
