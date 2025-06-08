import { Gauge } from "lucide-react"
import { Bar, BarChart, CartesianGrid, XAxis } from "recharts"
import { dadosGraficoDobramento } from "@/data/data"

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
  dadosGraficoDobramento.map((valor) => {
    media += valor.ms
  })
  media = media / dadosGraficoDobramento.length
  media = Math.round(media);
  return media;
}

export function BarrasTempoPorHashDobramento() {
  return (
    <Card className="grid grid-cols-2-col gap-4 col-span-3">
      <CardHeader>
        <CardTitle>Gráfico do Hash de dobramento</CardTitle>
        <CardDescription>Compara a inserção das quantidades de dados</CardDescription>
      </CardHeader>
      <CardContent >
        <ChartContainer config={chartConfig} >
          <BarChart accessibilityLayer data={dadosGraficoDobramento}>
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="nome"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
              tickFormatter={(_, value) => (dadosGraficoDobramento[value]?.legenda ?? "").slice(0, 4)}
            />
            <ChartTooltip
              cursor={false}
              content={<ChartTooltipContent indicator="dashed" />}
            />
            <Bar dataKey="ms" fill="var(--chart-3)" radius={4} />
          </BarChart>
        </ChartContainer>
      </CardContent>
      <CardFooter className="flex-col items-start gap-2 text-sm">
        <div className="flex gap-2 leading-none font-medium">
          Execução média: {calculaVariacao()} ms<Gauge className="h-4 w-4" />
        </div>
        <div className="text-muted-foreground leading-none">
          Mostra o hash de dobramento e seus respectivos tempos de acordo com
          cada quantidade de dados.
        </div>
      </CardFooter>
    </Card>
  )
}
