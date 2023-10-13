package br.com.capisorteios_ktx.model

data class Consumidor (
    var nomeCompleto : String? = null,
    var cpf : String? = null,
    var email : String? = null,
    var senha : String? = null,
    var chavePix : String? = null,
    var isAdm : Boolean = false
)