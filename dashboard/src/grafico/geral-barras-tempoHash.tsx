import { Gauge } from "lucide-react"
import { Bar, BarChart, CartesianGrid, XAxis } from "recharts"
import { dadosGraficoGeral } from "@/data/data"

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
  vinteKK: {
    label: "20KK",
    color: "var(--chart-1)",
  },
  cincoKK: {
    label: "5KK",
    color: "var(--chart-2)",
  },
  umKK: {
    label: "1KK",
    color: "var(--chart-3)",
  },
} satisfies ChartConfig
function calculaVariacao(): number {
  let media: number = 0;
  dadosGraficoGeral.map((valor) => {
    media += valor.vinteKK
    media += valor.cincoKK
    media += valor.umKK
  })
  media = media / (3 * dadosGraficoGeral.length)
  media = Math.round(media);
  return media;
}

export function BarrasTempoPorHashGeral() {
  return (
    <Card className="grid grid-cols-2-col col-span-6 gap-4">
      <CardHeader>
        <CardTitle>Gráfico de barras geral</CardTitle>
        <CardDescription>Comparativo dos tipos de hash</CardDescription>
      </CardHeader>
      <CardContent>
        <ChartContainer config={chartConfig} className="h-[240px] w-full">
            <BarChart accessibilityLayer data={dadosGraficoGeral}>
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="nome"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
              tickFormatter={(_, valor) => dadosGraficoGeral[valor]?.legenda ?? ""}
            />
            <ChartTooltip
              cursor={false}
              content={<ChartTooltipContent indicator="dashed" />}
            />
            <Bar dataKey="umKK" fill="var(--color-umKK)" stackId='a' />
            <Bar dataKey="cincoKK" fill="var(--color-cincoKK)" stackId='a' />
            <Bar dataKey="vinteKK" fill="var(--color-vinteKK)" stackId='a' />
            </BarChart>
        </ChartContainer>
      </CardContent>
      <CardFooter className="flex-col items-start gap-2 text-sm">
        <div className="flex gap-2 leading-none font-medium">
          Execução média geral: {calculaVariacao()} ms<Gauge className="h-4 w-4" />
        </div>
        <div className="text-muted-foreground leading-none">
          Mostra todos os tipos de hash e seus respectivos tempos de acordo com
          cada quantidade de dados.
        </div>
      </CardFooter>
    </Card>
  )
}
