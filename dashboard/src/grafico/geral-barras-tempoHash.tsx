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
  { nome: "Hash dobramento", vinteKK: 186, dezKK: 80, umKK: 23 },
  { nome: "Hash multiplicação", vinteKK: 166, dezKK: 210, umKK: 21 },
  { nome: "Hash resto", vinteKK: 116, dezKK: 230, umKK: 246 },

]

const chartConfig = {
  vinteKK: {
    label: "20KK",
    color: "var(--chart-1)",
  },
  dezKK: {
    label: "10KK",
    color: "var(--chart-2)",
  },
   umKK: {
    label: "1KK",
    color: "var(--chart-3)",
  },
} satisfies ChartConfig
function calculaVariacao(): number {
  let media: number = 0;
  chartData.map((value) => {
    media += value.vinteKK
    media += value.dezKK
    media += value.umKK
  })
  media = media / ( 3 * chartData.length )
  media = Math.round(media);
  return media;
}

export function BarrasTempoPorHashGeral() {
  return (
    <Card className="grid grid-cols-2-col gap-4">
      <CardHeader>
        <CardTitle>Gráfico de barras geral</CardTitle>
        <CardDescription>Comparativo dos tipos de hash</CardDescription>
      </CardHeader>
      <CardContent>
        <ChartContainer config={chartConfig}>
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
            <Bar dataKey="umKK" fill="var(--color-umKK)" radius={4} />
            <Bar dataKey="dezKK" fill="var(--color-dezKK)" radius={4} />
            <Bar dataKey="vinteKK" fill="var(--color-vinteKK)" radius={4} />
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
