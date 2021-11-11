package br.edu.unifio.setimoprojeto.bean;

import br.edu.unifio.setimoprojeto.domain.Compra;
import br.edu.unifio.setimoprojeto.repository.CompraRepository;
import lombok.Data;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
@Data
public class CompraBean {
    private Compra compra;
    private List<Compra> compras;
    @Autowired
    private CompraRepository compraRepository;

    public void novo() {
        compra = new Compra();

    }


    public void listar(){
        compras = compraRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public void salvar() {
        try {

            compraRepository.save(compra);
            Messages.addFlashGlobalInfo("Compra salva com sucesso");
            novo();
            Faces.navigate("compra-listagem.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            Messages.addGlobalError("já existe uma compra cadastrada para o nome informado");
        }
    }
    public void selecionarEdicao(Compra compra) {
        Faces.setFlashAttribute("compra", compra);
        Faces.navigate("compra-edicao.xhtml?faces-redirect=true");
    }
    public void selecionarExclusao(Compra compra){
        Faces.setFlashAttribute("compra", compra);
        Faces.navigate("compra-exclusao.xhtml?faces-redirect=true");
    }

    public void carregar(){
        compra =  Faces.getFlashAttribute("Compra");

        if(compra == null){
            Faces.navigate("compra-listagem.xhtml?faces-redirect=true");
        }
    }
    public void excluir() {
        try {

            compraRepository.deleteById(compra.getCodCompra());
            Messages.addFlashGlobalInfo("Compra removida com sucesso");
            novo();
            Faces.navigate("compra-listagem.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            Messages.addGlobalError("A Compra está vinculado com outro registro");
        }
    }
}
