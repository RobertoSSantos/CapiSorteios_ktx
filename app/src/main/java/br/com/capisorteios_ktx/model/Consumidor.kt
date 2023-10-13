package br.com.capisorteios_ktx.model

data class Consumidor (
    var nomeCompleto : String = "",
    var cpf : String = "",
    var email : String = "",
    var senha : String = "",
    var chavePix : String = "",
    var isAdm : Boolean = false
)