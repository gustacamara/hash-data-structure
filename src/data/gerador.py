import random
import os

# OBS todos os exemplos foram gerados com esse programa
# 1 milhão - seed 1
# 5 milhões - seed 5
# 20 milhões - seed 20

semente = int(input("Digite a semente: "))
quantidade = int(input("Digite a quantidade: "))
nome_arquivo = input("Nome do arquivo:") + '.txt'

if nome_arquivo == '' or nome_arquivo == '/n':
    nome_arquivo = 'numeros'

random.seed(semente)

strings = []
for _ in range(quantidade):
    num = random.randint(0, 999999999)
    strings.append(f"{num:09d}")

caminho_completo = os.path.abspath(nome_arquivo)

with open(nome_arquivo, "w") as arquivo:
    arquivo.write("\n".join(strings))

print(f"{quantidade} strings geradas.")
print(f"Arquivo salvo em: {caminho_completo}")
