#language:pt

  Funcionalidade: Preencher o formulário de FAP
    Para que eu possa preencher o formulário de FAP
    Sendo um usuário comum
    Posso fazer minha estimativa de FAP

    Cenario: Deve preencher o formulário  FAP com sucesso
      Dado que esteja na página FAP
      Quando preencher todos os campos do formulário
      E clicar no botão Estimar FAP
      Entao deve abrir uma nova página com a "ESTIMATIVA FAP DA EMPRESA"

