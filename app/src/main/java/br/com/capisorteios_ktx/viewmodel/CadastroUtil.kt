package br.com.capisorteios_ktx.viewmodel

import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate

object CadastroUtil {

    /**
     *  Os registros não serão validos se
     *  houver qualquer valor vazio,
     *  data não estiver no formato especificado "dd/mm/aaaa"
     *  data não condizer com valores reais
     */

    fun validarCadastroRifa(
        titulo: String,
        autor: String,
        premio: String,
        valor: String,
        dataFinal: String
    ): Boolean {
        var retorno: Boolean = true

        if(titulo == "" || autor == "" || premio == "" || valor == "" || dataFinal == ""){
            retorno = false
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        dateFormat.isLenient = false

        try {
            dateFormat.parse(dataFinal)
        } catch (e: Exception) {
            retorno = false
        }

        return retorno
    }

    fun validarCadastroUsuario(
        nomeCompleto : String?,
        cpf : String?,
        email : String,
        senha : String,
        chavePix : String?,
        isAdm : Boolean
    ) : Boolean{
        var retorno : Boolean = true

        if (nomeCompleto == "" || cpf == "" || email == "" || senha == "" || chavePix == "") {
            retorno = false
        }

        return retorno
    }
}