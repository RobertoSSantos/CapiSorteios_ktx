package br.com.capisorteios_ktx

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Equipe:
 * Antônio Horácio
 * Joseph Neiva
 * Matheus Sá
 * Pedro Menezes
 * Roberto Santos
 */

class CadastroUtilTest{

    @Test
    fun `todos os dados preenchidos corretamente`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa de Relogio",
            "Roberto",
            "Relogio",
            "30,00",
            "05/10/2023"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `titulo vazio e resto dos valores preenchidos corretamente`(){
        val result = CadastroUtil.validarCadastroRifa(
            "",
            "Roberto",
            "Relogio",
            "30,00",
            "05/10/2023"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `autor vazio e resto dos valores preenchidos corretamente`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa Relogio",
            "",
            "Relogio",
            "30,00",
            "05/10/2023"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `premio vazio e resto dos valores preenchidos corretamente`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa Relogio",
            "Roberto",
            "",
            "30,00",
            "05/10/2023"
        )

        assertThat(result).isFalse()
    }
    @Test
    fun `valor vazio e resto dos valores preenchidos corretamente`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa Relogio",
            "Roberto",
            "Relogio",
            "",
            "05/10/2023"
        )

        assertThat(result).isFalse()
    }
    @Test
    fun `data final vazia e resto dos valores preenchidos corretamente`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa Relogio",
            "Roberto",
            "Relogio",
            "30,00",
            ""
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `Dados completos e data fora do formato requerido`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa Relogio",
            "Roberto",
            "Relogio",
            "30,00",
            "05.10.2023"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `Dados completos e data fora do escopo real`(){
        val result = CadastroUtil.validarCadastroRifa(
            "Rifa Relogio",
            "Roberto",
            "Relogio",
            "30,00",
            "31/02/2023"
        )

        assertThat(result).isFalse()
    }

}