import './global.css'
import { BarrasTempoColisoesUmKK } from './grafico/busca-barras-tempoColisoes1kk'
import { BarrasTempoColisoesVinteKK } from './grafico/busca-barras-tempoColisoes20kk'
import { BarrasTempoColisoesCincoKK } from './grafico/busca-barras-tempoColisoes5kk'
import { BarrasTempoPorHashDobramento } from './grafico/dobramento-barras-tempoHash'
import { BarrasTempoPorHashGeral } from './grafico/geral-barras-tempoHash'
import { BarrasTempoPorHashMultiplicacao } from './grafico/multiplicacao-barras-tempoHash'
import { BarrasTempoPorHashResto } from './grafico/resto-barras-tempoHash'

export function App() {

  return (
    <div className='w-screen'>
      <div className='m-5'>
        <h1 className='text-3xl font-bold my-2'>Análise de performace</h1>
        <div className='flex flex-col gap-4'>
          <div className='grid grid-cols-9 gap-4'>
            <BarrasTempoPorHashGeral />
            <BarrasTempoPorHashDobramento />
          </div>
          <div className='grid grid-cols-7 gap-4'>
            <BarrasTempoPorHashMultiplicacao />
            <BarrasTempoPorHashResto />
            <BarrasTempoColisoesUmKK />
          </div>
          <div className='grid grid-cols-2 gap-4'>
            <BarrasTempoColisoesCincoKK/>
            <BarrasTempoColisoesVinteKK/>
          </div>
        </div>
      </div>
    </div>
  )
}
