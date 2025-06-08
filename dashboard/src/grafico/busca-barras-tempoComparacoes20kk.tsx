import { Gauge } from "lucide-react"
import { Bar, BarChart, CartesianGrid, XAxis } from "recharts"
import { comparacoesVinte } from "@/data/data"

import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import {
  type ChartConfig,
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from "@/components/ui/chart"

const chartConfig = {
  ms: {
    label: "ms",
    color: "var(--chart-2)",
  },
  colisoes: {
    label: "Comparações",
    color: "var(--chart-3)",
  },
} satisfies ChartConfig
function calculaVariacao(): number {
  let media: number = 0
  comparacoesVinte.map((valor) => {
    media += valor.ms
  })
  media = media / comparacoesVinte.length
  media = Math.round(media)
  return media
}

export function BarrasTempoComparacoesVinteKK() {
  return (
    <Card className="grid grid-cols-2-col col-span-1 gap-4">
      <CardHeader>
        <CardTitle>Gráfico de Colisões e buscas</CardTitle>
        <CardDescription>
          Comparativo com <span className="text-accent-foreground">20 Milhões</span>
        </CardDescription>
      </CardHeader>
      <CardContent>
        <ChartContainer config={chartConfig} className="h-[240px] w-full">
          <BarChart accessibilityLayer data={comparacoesVinte}>
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="nome"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
              tickFormatter={(_, valor) => (comparacoesVinte[valor]?.legenda ?? "").slice(0, 4)}
            />
            <ChartTooltip
              cursor={false}
              content={<ChartTooltipContent indicator="dashed" />}
            />
            <Bar dataKey="ms" fill="var(--color-ms)" radius={4} />
            <Bar dataKey="comparacoes" fill="var(--color-colisoes)" radius={4} />
          </BarChart>
        </ChartContainer>
      </CardContent>
      <CardFooter className="flex-col items-start gap-2 text-sm">
        <div className="flex gap-2 leading-none font-medium">
          Execução média geral: {calculaVariacao()} ms<Gauge className="h-4 w-4" />
        </div>
        <div className="text-muted-foreground leading-none">
          Mostra a quantidade de colisões e o tempo para efetuar a busca
          com <span className="text-accent-foreground">20 Milhão</span> de linhas.
        </div>
      </CardFooter>
    </Card>
  )
}
