import { Gauge } from "lucide-react"
import { Bar, BarChart, CartesianGrid, XAxis } from "recharts"

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

export const description = "A multiple bar chart"

const chartData = [
  { nome: "Dobramento", ms: 186, colisoes: 12 },
  { nome: "Dobramento", ms: 145, colisoes: 45 },
  { nome: "Dobramento", ms: 210, colisoes: 45 },
  { nome: "Multiplicação", ms: 186, colisoes: 12 },
  { nome: "Multiplicação", ms: 145, colisoes: 45 },
  { nome: "Multiplicação", ms: 210, colisoes: 45 },
  { nome: "Divisão", ms: 186, colisoes: 12 },
  { nome: "Divisão", ms: 145, colisoes: 45 },
  { nome: "Divisão", ms: 210, colisoes: 45 },
]

const chartConfig = {
  ms: {
    label: "ms",
    color: "var(--chart-2)",
  },
  colisoes: {
    label: "colisoes",
    color: "var(--chart-3)",
  },
} satisfies ChartConfig
function calculaVariacao(): number {
  let media: number = 0
  chartData.map((value) => {
    media += value.ms
  })
  media = media / chartData.length
  media = Math.round(media)
  return media
}

export function BarrasTempoColisoesVinteKK() {
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
          <BarChart accessibilityLayer data={chartData}>
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="nome"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
              tickFormatter={(value) => value.slice(0, 3)}
            />
            <ChartTooltip
              cursor={false}
              content={<ChartTooltipContent indicator="dashed" />}
            />
            <Bar dataKey="ms" fill="var(--color-ms)" radius={4} />
            <Bar dataKey="colisoes" fill="var(--color-colisoes)" radius={4} />
            <Bar dataKey="vinteKK" fill="var(--color-vinteKK)" radius={4} />
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
