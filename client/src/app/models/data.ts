import { TableInfo, TableRoute } from './tableInfo';
import { environment } from '../../environments/environment';
import { Validators } from '@angular/forms';

const Drzava: TableInfo = {
  kolone: ['Sifra drzave', 'Naziv drzave'],
  atributi: ['id', 'nazivDrzave'],
  naslov: 'Drzave',
  podaciUrl: environment.hostUrl + '/api/drzave',
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
    { labela: 'Valuta', ruta: '/valute' },
    { labela: 'Fizicko lice', ruta: '/fizickaLica' }
  ]
};

const Valuta: TableInfo = {
  kolone: ['Id valute', 'Zvanicna sifra', 'Naziv', 'Domicilna', 'Drzava'],
  atributi: ['id', 'zvanicnaSifra', 'naziv', 'domicilna', 'drzavaId'],
  naslov: 'Valute',
  podaciUrl: environment.hostUrl + '/api/valute',
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
  podaciUrl: environment.hostUrl + '/api/naseljenaMesta',
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
  ],
  potomci: [
    { labela: 'Fizicko lice', ruta: '/fizickaLica' },
    { labela: 'Pravna lica', ruta: '/pravnaLica' }
  ]
};

const AtributiBanke: TableInfo = {
  kolone: ['Sifra banke', 'Swift kod'],
  atributi: ['id', 'swiftKod'],
  naslov: 'Atributi banaka',
  podaciUrl: environment.hostUrl + '/api/atributiBanke',
  prikazForme: [
    { labela: 'Sifra banke', tip: 'text', imeKontrole: 'id' },
    { labela: 'Swift kod', tip: 'text', imeKontrole: 'swiftKod' }
  ],
  modelForme: {
    id: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(3)]],
    swiftKod: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(8)]]
  },
  potomci: [
    { labela: 'Pravna lica', ruta: '/pravnaLica' }
  ]
};

const FizickoLice: TableInfo = {
  kolone: ['Id lica', 'Adresa', 'Id naseljenog mesta', 'Ime', 'Prezime', 'JMBG', 'Broj licne karte', 'Drzavljanstvo'],
  atributi: ['id', 'adresa', 'naseljenomestoId', 'ime', 'prezime', 'jmbg', 'brojLicneKarte', 'drzavaId'],
  naslov: 'Fizicka lica',
  podaciUrl: environment.hostUrl + '/api/lica/fizickalica',
  prikazForme: [
    { labela: 'Adresa', tip: 'text', imeKontrole: 'adresa' },
    { labela: 'Id naseljenog mesta', tip: 'number', imeKontrole: 'naseljenomestoId', table: NaseljenoMesto, roditeljskaKolona: 'id' },
    { labela: 'Ime', tip: 'text', imeKontrole: 'ime' },
    { labela: 'Prezime', tip: 'text', imeKontrole: 'prezime' },
    { labela: 'JMBG', tip: 'number', imeKontrole: 'jmbg' },
    { labela: 'Broj licne karte', tip: 'number', imeKontrole: 'brojLicneKarte' },
    { labela: 'Id drzave(drzavljanstvo)', tip: 'number', imeKontrole: 'drzavaId', table: Drzava, roditeljskaKolona: 'id' },
  ],
  modelForme: {
    adresa: [null, [Validators.required, Validators.maxLength(250)]],
    naseljenomestoId: [null, Validators.required],
    ime: [null, [Validators.required, Validators.maxLength(120)]],
    prezime: [null, [Validators.required, Validators.maxLength(120)]],
    jmbg: [null, [Validators.required, Validators.minLength(13), Validators.maxLength(13)]],
    brojLicneKarte: [null, [Validators.required, Validators.minLength(9), Validators.maxLength(9)]],
    drzavaId: [null, Validators.required]
  },
  preci: [
    { labela: 'Drzave', ruta: '/drzave' },
    { labela: 'Naseljena mesta', ruta: '/naseljenaMesta' }
  ]
};

const PravnoLice: TableInfo = {
  kolone: ['Id lica', 'Adresa', 'Id naseljenog mesta', 'Naziv', 'Maticni broj', 'PIB', 'E-mail', 'Web', 'Telefon', 'Faks', 'Banka Id'],
  atributi: ['id', 'adresa', 'naseljenomestoId', 'naziv', 'maticniBroj', 'pib', 'email', 'web', 'telefon', 'faks', 'atributiBankeId'],
  naslov: 'Pravna lica',
  podaciUrl: environment.hostUrl + '/api/lica/pravnalica',
  prikazForme: [
    { labela: 'Adresa', tip: 'text', imeKontrole: 'adresa' },
    { labela: 'Id naseljenog mesta', tip: 'number', imeKontrole: 'naseljenomestoId', table: NaseljenoMesto, roditeljskaKolona: 'id' },
    { labela: 'Naziv', tip: 'text', imeKontrole: 'naziv' },
    { labela: 'Maticni Broj', tip: 'number', imeKontrole: 'maticniBroj' },
    { labela: 'PIB', tip: 'number', imeKontrole: 'pib' },
    { labela: 'E-mail', tip: 'text', imeKontrole: 'email' },
    { labela: 'Web', tip: 'text', imeKontrole: 'web' },
    { labela: 'Telefon', tip: 'text', imeKontrole: 'telefon' },
    { labela: 'Faks', tip: 'text', imeKontrole: 'faks' },
    {
      labela: 'Atributi banke(pravno lice je banka)',
      tip: 'text',
      imeKontrole: 'atributiBankeId',
      table: AtributiBanke,
      roditeljskaKolona: 'id'
    }
  ],
  modelForme: {
    adresa: [null, [Validators.required, Validators.maxLength(250)]],
    naseljenomestoId: [null, Validators.required],
    naziv: [null, [Validators.required, Validators.maxLength(120)]],
    maticniBroj: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
    pib: [null, [Validators.required, Validators.minLength(9), Validators.maxLength(9)]],
    email: [null, Validators.maxLength(128)],
    web: [null, Validators.maxLength(128)],
    telefon: [null, Validators.maxLength(20)],
    faks: [null, Validators.maxLength(20)],
    atributiBankeId: [null]
  },
  preci: [
    { labela: 'Atributi banke', ruta: '/atributiBanaka' },
    { labela: 'Naseljena mesta', ruta: '/naseljenaMesta' }
  ]
};

const Banka: TableInfo = {
  kolone: ['Id lica', 'Adresa', 'Id naseljenog mesta', 'Naziv', 'Maticni broj', 'PIB', 'E-mail', 'Web', 'Telefon', 'Faks', 'Banka Id'],
  atributi: ['id', 'adresa', 'naseljenomestoId', 'naziv', 'maticniBroj', 'pib', 'email', 'web', 'telefon', 'faks', 'atributiBankeId'],
  naslov: 'Banke',
  podaciUrl: environment.hostUrl + '/api/lica/pravnalica/banke',
  prikazForme: [
  ],
  modelForme: {
  }
};

const Lice: TableInfo = {
  kolone: ['Id lica', 'Adresa', 'Id naseljenog mesta'],
  atributi: ['id', 'adresa', 'naseljenomestoId'],
  naslov: 'Lica',
  podaciUrl: environment.hostUrl + '/api/lica/pravnalica/lica',
  prikazForme: [
  ],
  modelForme: {
  }
};

const Racun: TableInfo = {
  kolone: ['Id racuna', 'Broj racuna', 'Datum otvaranja', 'validan', 'Id vlasnika', 'Id banke', 'Id valute'],
  atributi: ['id', 'brojRacuna', 'datumOtvaranja', 'validan', 'liceId', 'bankaId', 'valutaId'],
  naslov: 'Racuni',
  podaciUrl: environment.hostUrl + '/api/racuni',
  prikazForme: [
    { labela: 'Broj racuna', tip: 'text', imeKontrole: 'brojRacuna' },
    { labela: 'Id lica', tip: 'number', imeKontrole: 'liceId', table: Lice, roditeljskaKolona: 'id' },
    { labela: 'Id banke', tip: 'number', imeKontrole: 'bankaId', table: Banka, roditeljskaKolona: 'id' },
    { labela: 'Id valuta', tip: 'number', imeKontrole: 'valutaId', table: Valuta, roditeljskaKolona: 'id' },
  ],
  modelForme: {
    brojRacuna: [null, [Validators.required, Validators.minLength(18), Validators.maxLength(18)]],
    liceId: [null, Validators.required],
    bankaId: [null, Validators.required],
    valutaId: [null, Validators.required],
  }
};

const KursnaLista: TableInfo = {
  kolone: ['Id kursne liste', 'Datum', 'Broj kursne liste', 'Datum vazenja', 'Sifra banke'],
  atributi: ['id', 'datum', 'brojKursneListe', 'datumVazenja', 'sifraBanke'],
  naslov: 'Kursne liste',
  podaciUrl: environment.hostUrl + '/api/kursneListe',
  prikazForme: [
    { labela: 'Broj kursne liste', tip: 'number', imeKontrole: 'brojKursneListe' },
    { labela: 'Datum vazenja', tip: 'date', imeKontrole: 'datumVazenja' },
    { labela: 'Sifra banke', tip: 'number', imeKontrole: 'sifraBanke', table: Banka, roditeljskaKolona: 'id' }
  ],
  modelForme: {
    brojKursneListe: [null, [Validators.required, Validators.maxLength(3)]],
    datumVazenja: [null, Validators.required],
    sifraBanke: [null, Validators.required],
  }
};

const KursUValuti: TableInfo = {
  kolone: ['Id kursa', 'Kupovni', 'Srednji', 'Prodajni', 'Sifra kursne liste', 'Sifra Osnovne Valute', 'Sifra prema Valuti'],
  atributi: ['id', 'kupovni', 'srednji', 'prodajni', 'sifraKursneListe', 'sifraOsnovneValute', 'sifraPremaValuti'],
  naslov: 'Kursevi u valuti',
  podaciUrl: environment.hostUrl + '/api/kursevi',
  prikazForme: [
    { labela: 'Id kursa', tip: 'number', imeKontrole: 'id' },
    { labela: 'Kupovni', tip: 'number', imeKontrole: 'kupovni' },
    { labela: 'Prodajni', tip: 'number', imeKontrole: 'prodajni' },
    { labela: 'Sifra kursne liste', tip: 'number', imeKontrole: 'sifraKursneListe', table: KursnaLista, roditeljskaKolona: 'id' },
    { labela: 'Sifra Osnovne valute', tip: 'number', imeKontrole: 'sifraOsnovneValute', table: Valuta, roditeljskaKolona: 'id' },
    { labela: 'Sifra prema valuti', tip: 'number', imeKontrole: 'sifraPremaValuti', table: Valuta, roditeljskaKolona: 'id' }
  ],
  modelForme: {
    id: [null, [Validators.required, Validators.maxLength(9)]],
    kupovni: [null, [Validators.required, Validators.maxLength(17)]],
    prodajni: [null, [Validators.required, Validators.maxLength(17)]],
    sifraKursneListe: [null, Validators.required],
    sifraOsnovneValute: [null, Validators.required],
    sifraPremaValuti: [null, Validators.required],
  }
};

const VrstaPlacanja: TableInfo = {
  kolone: ['Id vrste placanja', 'Naziv'],
  atributi: ['id', 'naziv'],
  naslov: 'Vrste placanja',
  podaciUrl: environment.hostUrl + '/api/vrstaPlacanja',
  prikazForme: [
    { labela: 'Id vrste placanja', tip: 'number', imeKontrole: 'id' },
    { labela: 'Naziv vrste placanja', tip: 'text', imeKontrole: 'naziv' },
  ],
  modelForme: {
    id: [null, [Validators.required, Validators.maxLength(3)]],
    naziv: [null, Validators.required],
  }
};

export const TableRoutes: TableRoute[] = [
  { ruta: 'drzave', labela: 'Drzave', data: Drzava },
  { ruta: 'valute', labela: 'Valute', data: Valuta },
  { ruta: 'naseljenaMesta', labela: 'Naseljena mesta', data: NaseljenoMesto },
  { ruta: 'atributiBanaka', labela: 'Atributi banaka', data: AtributiBanke },
  { ruta: 'fizickaLica', labela: 'Fizicka Lica', data: FizickoLice },
  { ruta: 'pravnaLica', labela: 'Pravna Lica', data: PravnoLice },
  { ruta: 'racuni', labela: 'Racuni', data: Racun },
  { ruta: 'kursnaLista', labela: 'Kursne liste', data: KursnaLista },
  { ruta: 'kursevi', labela: 'Kursevi u valuti', data: KursUValuti },
  { ruta: 'vrstePlacanja', labela: 'Vrste Placanja', data: VrstaPlacanja}
];
