import { TableInfo, TableRoute } from './tableInfo';
import { environment } from '../../environments/environment';
import { Validators } from '@angular/forms';

const Drzava: TableInfo = {
  kolone: ['Sifra drzave', 'Naziv drzave'],
  atributi: ['id', 'nazivDrzave'],
  naslov: 'Drzave',
  podaciUrl: environment.hostUrl  + '/api/drzave',
  prikazForme: [
    { labela: 'Sifra drzave', tip: 'number', imeKontrole: 'id' },
    { labela: 'Naziv drzave', tip: 'text', imeKontrole: 'nazivDrzave' }
  ],
  modelForme: {
    id: [null, Validators.required],
    nazivDrzave: [null, Validators.required]
  },
  potomci: [
    { labela: 'Naseljeno mesto', ruta: '/naseljenaMesta' },
    { labela: 'Valuta', ruta: '/valute' }
  ]
};

const Valuta: TableInfo = {
  kolone: ['Id valute', 'Zvanicna sifra', 'Naziv', 'Domicilna', 'Drzava'],
  atributi: ['id', 'zvanicnaSifra', 'naziv', 'domicilna', 'drzavaId'],
  naslov: 'Valute',
  podaciUrl: environment.hostUrl  + '/api/valute',
  prikazForme: [
    { labela: 'Zvanicna sifra', tip: 'number', imeKontrole: 'zvanicnaSifra' },
    { labela: 'Naziv', tip: 'text', imeKontrole: 'naziv' },
    { labela: 'Sifra drzave', tip: 'number', imeKontrole: 'drzavaId', table: Drzava, roditeljskaKolona: 'id' },
    { labela: 'Domicilna', tip: 'checkbox', imeKontrole: 'domicilna' },
  ],
  modelForme: {
    zvanicnaSifra: [null, [Validators.required, Validators.maxLength(3), Validators.minLength(3)]],
    naziv: [null, [Validators.required, Validators.maxLength(30)]],
    drzavaId: [null, Validators.required],
    domicilna: [false, Validators.required]
  },
  preci: [
    { labela: 'Drzave', ruta: '/drzave' },
  ]
};

const NaseljenoMesto: TableInfo = {
  kolone: ['Sifra mesta', 'Naziv', 'PTToznaka', 'Id drzave'],
  atributi: ['id', 'naziv', 'ptt', 'drzavaId'],
  naslov: 'Naseljena mesta',
  podaciUrl: environment.hostUrl  + '/api/naseljenaMesta',
  prikazForme: [
    { labela: 'Naziv mesta', tip: 'text', imeKontrole: 'naziv' },
    { labela: 'PTToznaka', tip: 'text', imeKontrole: 'ptt' },
    { labela: 'Sifra drzave', tip: 'number', imeKontrole: 'drzavaId', table: Drzava, roditeljskaKolona: 'id' }
  ],
  modelForme: {
    naziv: [null, [Validators.required, Validators.maxLength(60)]],
    ptt: [null, [Validators.required, Validators.maxLength(12)]],
    drzavaId: [null, Validators.required]
  },
  preci: [
    { labela: 'Drzave', ruta: '/drzave' },
  ]
};

export const TableRoutes: TableRoute[] = [
  { ruta: 'drzave', labela: 'Drzave', data: Drzava },
  { ruta: 'valute', labela: 'Valute', data: Valuta },
  { ruta: 'naseljenaMesta', labela: 'Naseljena mesta', data: NaseljenoMesto }
];
