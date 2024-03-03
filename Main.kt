package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            0 -> {
                print("Digite o nome do cachorro: ")
                val nome = readLine() ?: ""
                print("Digite a idade do cachorro: ")
                val idade = readLine()?.toIntOrNull() ?: 0
                val cachorro = Cachorro(idade, Color.BRANCO)
                cachorro.nome = nome
                repositorioAnimal.adicionar(cachorro)
            }
            1 -> {
                print("Digite o nome do gato: ")
                val nome = readLine() ?: ""
                print("Digite a idade do gato: ")
                val idade = readLine()?.toIntOrNull() ?: 0
                val gato = Gato(idade, Color.PRETO)
                gato.nome = nome
                repositorioAnimal.adicionar(gato)
            }
            2 -> {
                print("Digite o nome do pássaro: ")
                val nome = readLine() ?: ""
                print("Digite a idade do pássaro: ")
                val idade = readLine()?.toIntOrNull() ?: 0
                val passaro = Passaro(idade, Color.MARROM)
                passaro.nome = nome
                repositorioAnimal.adicionar(passaro)
            }
            3 -> {
                repositorioAnimal.listar()
            }
            4 -> {
                //repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            5 -> {
                print("Digite o nome do animal que você quer remover: ")
                val nomeAnimal = readlnOrNull()?: ""
                if (repositorioAnimal.remover(nomeAnimal)) {
                    println("$nomeAnimal removido.")
                } else {
                    println("Não foi encontrado nenhum animal com esse nome.")
                }
            }
            6 -> {
                print("Digite a cor: ")
                val cor = readlnOrNull()?: ""
                println("Animais com a cor $cor: ")
                repositorioAnimal.listarPorCor(cor)
            }
            7 -> {
                print("Digite a idade: ")
                val idade = readlnOrNull()?.toInt() ?: 0
                println("Animais com a idade $idade: ")
                repositorioAnimal.listarPorIdade(idade)
            }
            8 -> {
                print("Digite o nome: ")
                val nomeAnimal = readlnOrNull()?: ""
                val animalBuscado = repositorioAnimal.buscarPorNome(nomeAnimal)
                if (animalBuscado != null) {
                    println("O animal $nomeAnimal foi encontrado.")
                } else {
                    println("O animal $nomeAnimal não foi encontrado.")
                }
            }
            9 -> {
                print("Digite o nome do homem: ")
                val nome = readLine() ?: ""
                print("Digite a idade do homem: ")
                val idade = readLine()?.toIntOrNull() ?: 0
                val homem = Homem(idade, Color.MARROM)
                homem.nome = nome
                repositorioAnimal.adicionar(homem)
            }
        }

    }
}

enum class Color {
    MARROM, PRETO, BRANCO
}

abstract class Animal(var idade: Int, var cor: Color) {
    open var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int {
        return idade*7
    }
}

class Homem(idade: Int, cor: Color) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Alô")
    }

    override fun idadeEmAnosHumanos(): Int {
        return idade
    }
}

class Cachorro(idade: Int, cor: Color) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}
class Gato(idade: Int, cor: Color) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: Color) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

fun menu() {
    println("0 - Cachorro")
    println("1 - Gato")
    println("2 - Pássaro")
    println("3 - Listar Animais")
    println("4 - Emitir som")
    println("5 - Remover Animal")
    println("6 - Listar por Cor")
    println("7 - Listar por Idade")
    println("8 - Buscar por nome")
    println("9 - Homem")
    println("10 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun remover(nomeAnimal: String): Boolean {
        val animalARemover = buscarPorNome(nomeAnimal)
        if (animalARemover != null) {
            animais.remove(animalARemover)
            return true
        }
        return false
    }

    fun listarPorCor(cor: String) {
        val animaisCor = animais.filter { it.cor.toString() == cor.uppercase() }
        animaisCor.forEach { println(it.nome) }
    }

    fun listarPorIdade(idade: Int) {
        val animaisIdade = animais.filter { it.idade == idade }
        animaisIdade.forEach { println(it.nome) }
    }

    fun buscarPorNome(nomeAnimal: String): Animal? {
        return animais.find { it.nome == nomeAnimal }
    }
}
