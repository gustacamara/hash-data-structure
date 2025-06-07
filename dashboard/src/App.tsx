import './global.css'
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
          <div className='grid grid-cols-4 gap-4'>
            <BarrasTempoPorHashMultiplicacao />
            <BarrasTempoPorHashGeral />
            <BarrasTempoPorHashResto />
            <BarrasTempoPorHashDobramento />
          </div>
        </div>
      </div>
    </div>
  )
}
