import { Gauge } from "lucide-react"
import { Bar, BarChart, CartesianGrid, XAxis } from "recharts"
import { dadosGraficoMultiplicacao } from "@/data/data"

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
  },
} satisfies ChartConfig

function calculaVariacao(): number {
  let media: number = 0;
  dadosGraficoMultiplicacao.map((valor) => {
    media += valor.ms
  })
  media = media / dadosGraficoMultiplicacao.length
  media = Math.round(media);
  return media;
}

export function BarrasTempoPorHashMultiplicacao() {
  return (
    <Card className="grid cols-2-col gap-4 col-span-2">
      <CardHeader>
        <CardTitle>Gráfico do Hash de multiplicação</CardTitle>
        <CardDescription>Compara a inserção das quantidades de dados</CardDescription>
      </CardHeader>
      <CardContent>
        <ChartContainer config={chartConfig} className="h-[240px] w-full">
          <BarChart accessibilityLayer data={dadosGraficoMultiplicacao}>
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="nome"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
              tickFormatter={(_, value) => (dadosGraficoMultiplicacao[value]?.legenda ?? "").slice(0, 4)}

            />
            <ChartTooltip
              cursor={false}
              content={<ChartTooltipContent indicator="dashed" />}
            />
            <Bar dataKey="ms" fill="var(--chart-1)" radius={4} />
          </BarChart>
        </ChartContainer>
      </CardContent>
      <CardFooter className="flex-col items-start gap-2 text-sm">
        <div className="flex gap-2 leading-none font-medium">
          Execução média: {calculaVariacao()} ms<Gauge className="h-4 w-4" />
        </div>
        <div className="text-muted-foreground leading-none">
          Mostra o hash de multiplicação e seus respectivos tempos de acordo com
          cada quantidade de dados.
        </div>
      </CardFooter>
    </Card>
  )
}
