from flask import Flask, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

doencas = {
    'gripe': {
        'sintomas': ['febre', 'tosse', 'dor de cabeca']
    },
    'catapora': {
        'sintomas': ['manchas vermelhas', 'coceira', 'febre']
    
    },
    'bronquite': {
        'sintomas': ['tosse', 'producao de muco', 'cansaco', 'chiado no peito']
    },
    'diabetes': {
        'sintomas': ['sede excessiva', 'fome intensa', 'perda de peso', 'frequencia urinaria aumentada']
    },
    'hipertensao': {
        'sintomas': ['dores de cabeca', 'tonturas', 'visao embaçada', 'falta de ar']
    },
    'asma': {
        'sintomas': ['dificuldade de respirar', 'tosse', 'chiado', 'opressao no peito']
    },
    'dengue': {
        'sintomas': ['febre alta', 'dores musculares', 'dor atras dos olhos', 'manchas vermelhas na pele']
    },
    'zika': {
        'sintomas': ['febre baixa', 'conjuntivite', 'dor nas articulacoes', 'manchas vermelhas no corpo']
    },
    'chikungunya': {
        'sintomas': ['febre', 'dor nas articulacoes', 'edema', 'rash cutaneo']
    },
    'anemia': {
        'sintomas': ['fadiga', 'palidez', 'falta de ar', 'tontura']
    },
    'artrite': {
        'sintomas': ['dor nas articulacoes', 'inchaço', 'vermelhidao', 'diminuicao da amplitude de movimento']
    },
    'conjuntivite': {
        'sintomas': ['vermelhidao', 'coceira nos olhos', 'secrecao ocular', 'sensacao de areia nos olhos']
    },
    'gastrite': {
        'sintomas': ['dor ou ardor no estomago', 'nausea', 'vomito', 'sensacao de estomago cheio']
    },
    'hepatite': {
        'sintomas': ['ictericia', 'urina escura', 'fadiga extrema', 'nausea']
    },
    'malaria': {
        'sintomas': ['febre', 'calafrios', 'sudorese', 'dores de cabeca']
    },
    'meningite': {
        'sintomas': ['dor de cabeca severa', 'febre alta', 'rigidez no pescoco', 'sensibilidade a luz']
    },
    'prurido': {
        'sintomas': ['perda ponderal', 'sudorese noturna', 'fraqueza extrema', 'perda da sensibilidade']
    }
}



@app.route('/')
def home():
    return 'Bem-vindo à API de Doenças!'

@app.route('/doencas', methods=['GET'])
def listar_doencas():
    return jsonify(doencas)

@app.route('/doencas/<nome_doenca>', methods=['GET'])
def obter_doenca(nome_doenca):
    doenca = doencas.get(nome_doenca.lower())
    if doenca:
        return jsonify(doenca)
    else:
        return jsonify({"mensagem": "Doença não encontrada"}), 404

if __name__ == '__main__':
    app.run(debug=True)
