#language:pt

Funcionalidade: Buscar conteúdo
  Para que o usuário consiga realizar uma busca no blog, sendo um usuário comum

  Cenario: Busca com sucesso
    Dado que o usuário esteja na página de blog do site
    Quando o usuario realiza uma pesquisa no campo de busca
    Entao deve ver o texto "RESULTADO DA SUA BUSCA NO BLOG"
