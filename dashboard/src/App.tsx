import './global.css'
import { BarrasTempoComparacoesUmKK } from './grafico/busca-barras-tempoComparacoes1kk'
import { BarrasTempoComparacoesVinteKK } from './grafico/busca-barras-tempoComparacoes20kk'
import { BarrasTempoComparacoesCincoKK } from './grafico/busca-barras-tempoComparacoes5kk'
import { BarrasTempoPorHashDobramento } from './grafico/dobramento-barras-tempoHash'
import { BarrasTempoPorHashGeral } from './grafico/geral-barras-tempoHash'
import { BarrasTempoPorHashMultiplicacao } from './grafico/multiplicacao-barras-tempoHash'
import { BarrasTempoPorHashResto } from './grafico/resto-barras-tempoHash'

export function App() {

  return (
    <div className='w-screen'>
      <div className='m-5'>
        <h1 className='text-3xl font-bold'>Análise de performance</h1>
        <p className='text-muted-foreground mb-2'>
          <span className='font-bold'>Alunos:</span> Ana Paula, Carlos Nogueira, Giovanni Galarda, Luis Gustavo
        </p>
        <div className='flex flex-col gap-4'>
          <div className='grid grid-cols-9 gap-4'>
            <BarrasTempoPorHashGeral />
            <BarrasTempoPorHashDobramento />
          </div>
          <div className='grid grid-cols-7 gap-4'>
            <BarrasTempoPorHashMultiplicacao />
            <BarrasTempoPorHashResto />
            <BarrasTempoComparacoesUmKK />
          </div>
          <div className='grid grid-cols-2 gap-4'>
            <BarrasTempoComparacoesCincoKK />
            <BarrasTempoComparacoesVinteKK />
          </div>
        </div>
      </div>
    </div>
  )
}
