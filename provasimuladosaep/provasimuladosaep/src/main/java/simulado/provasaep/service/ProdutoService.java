package simulado.provasaep.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simulado.provasaep.dto.produto.ProdutoAtualizarDto;
import simulado.provasaep.dto.produto.ProdutoDto;
import simulado.provasaep.dto.produto.ProdutoRespostaDto;
import simulado.provasaep.entity.ProdutoEntity;
import simulado.provasaep.exceptions.ProdutoCadastradoException;
import simulado.provasaep.exceptions.ProdutoComMovimentacaoException;
import simulado.provasaep.exceptions.ProdutoNaoEncontradoException;
import simulado.provasaep.repository.MovimentacaoEstoqueRepository;
import simulado.provasaep.repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final MovimentacaoEstoqueRepository movimentacaoRepository;

    public List<ProdutoRespostaDto> listarProdutos() {
        return repository.findAll()
                .stream()
                .map(ProdutoRespostaDto::toProdutoRespostaDto)
                .toList();
    }

    public List<ProdutoRespostaDto> buscarPersonalizada(String termo) {
        List<ProdutoEntity> produtos = repository.
                findByCodigoContainingIgnoreCaseOrNomeContainingIgnoreCase(termo, termo);

        return produtos.stream()
                .map(ProdutoRespostaDto::toProdutoRespostaDto)
                .toList();
    }

    public void cadastrarProduto(ProdutoDto produtoDto) {
        if (repository.existsByCodigo(produtoDto.codigo())) {
            throw new ProdutoCadastradoException("Produto já cadastrado");
        }

        repository.save(produtoDto.toProduto());
    }

    public ProdutoEntity buscarProdutoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    public void atualizarProduto(Long id, ProdutoAtualizarDto produtoAtualizarDto) {
        ProdutoEntity produto = buscarProdutoPorId(id);

        produto.setNome(produtoAtualizarDto.nome());
        produto.setCaracteristicas(produtoAtualizarDto.caracteristicas());
        produto.setEstoqueMinimo(produtoAtualizarDto.estoqueMinimo());

        repository.save(produto);
    }

    public void excluirProduto(Long id) {
        buscarProdutoPorId(id);

        if (movimentacaoRepository.existsByProdutoId(id)) {
            throw new ProdutoComMovimentacaoException("Não é possível excluir o produto pois ele possui movimentações registradas");
        }
        repository.deleteById(id);
    }
}
