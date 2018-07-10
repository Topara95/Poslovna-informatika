export interface TableRoute {
  ruta: string;
  labela: string;
  data: TableInfo;
}

export interface TableInfo {
  kolone: string[];
  atributi: string[];
  naslov: string;
  podaciUrl: string;
  prikazForme: FormaView[];
  modelForme: any;
  potomci?: Relacija[];
  preci?: Relacija[];
}

export interface FormaView {
  labela: string;
  tip: string;
  imeKontrole: string;
  kolona?: string;
  roditeljskaKolona?: string;
  table?: TableInfo;
}

export interface Relacija {
  labela: string;
  ruta: string;
}
