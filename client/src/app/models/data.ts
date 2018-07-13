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
  ],
  mozeUnos: true
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
  ],
  potomci: [
    { labela: 'Kursevi', ruta: '/kursevi' },
    { labela: 'Racuni', ruta: '/racuni' },
    { labela: 'Analitike izvoda', ruta: '/analitikaIzvoda' },
    { labela: 'Medjubankarski nalozi', ruta: '/medjubankarskiNalog' }
  ],
  mozeUnos: true
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
    { labela: 'Pravna lica', ruta: '/pravnaLica' },
    { labela: 'Analitike izvoda', ruta: '/analitikaIzvoda' }
  ],
  mozeUnos: true
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
  ],
  mozeUnos: true
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
  ],
  potomci: [
    { labela: 'Racuni', ruta: '/racuni' }
  ],
  mozeUnos: true
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
  ],
  potomci: [
    { labela: 'Racuni', ruta: '/racuni' },
    { labela: 'Kursne Liste', ruta: '/kursnaLista' }
  ],
  mozeUnos: true
};

const Banka: TableInfo = {
  kolone: ['Id lica', 'Adresa', 'Id naseljenog mesta', 'Naziv', 'Maticni broj', 'PIB', 'E-mail', 'Web', 'Telefon', 'Faks', 'Banka Id'],
  atributi: ['id', 'adresa', 'naseljenomestoId', 'naziv', 'maticniBroj', 'pib', 'email', 'web', 'telefon', 'faks', 'atributiBankeId'],
  naslov: 'Banke',
  podaciUrl: environment.hostUrl + '/api/lica/pravnalica/banke',
  prikazForme: [
  ],
  modelForme: {
  },
  mozeUnos: false
};

const Lice: TableInfo = {
  kolone: ['Id lica', 'Adresa', 'Id naseljenog mesta'],
  atributi: ['id', 'adresa', 'naseljenomestoId'],
  naslov: 'Lica',
  podaciUrl: environment.hostUrl + '/api/lica/pravnalica/lica',
  prikazForme: [
  ],
  modelForme: {
  },
  mozeUnos: false
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
  },
  preci: [
    { labela: 'Valuta', ruta: '/valute' },
    { labela: 'Pravno Lice', ruta: '/pravnaLica' },
    { labela: 'Fizicko Lice', ruta: '/fizickaLica' }
  ],
  potomci: [
    { labela: 'Medjubakarski nalog', ruta: '/medjubankarskiNalog' },
    { labela: 'Ukidanja', ruta: '/ukidanje' },
    { labela: 'Dnevna stanja racuna', ruta: '/dnevnoStanjeRacuna' }
  ],
  mozeUnos: true
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
  },
  preci: [
    { labela: 'Pravno Lice', ruta: '/pravnaLica' }
  ],
  potomci: [
    { labela: 'Kursevi', ruta: '/kursevi' }
  ],
  mozeUnos: true
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
  },
  preci: [
    { labela: 'Kursna Lista', ruta: '/kursnaLista' },
    { labela: 'Valuta', ruta: '/valute' }
  ],
  mozeUnos: true
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
  },
  potomci: [
    { labela: 'Analitike izvoda', ruta: '/analitikaIzvoda' }
  ],
  mozeUnos: true
};

const DnevnoStanjeRacuna: TableInfo = {
  kolone: ['Id stanja', 'Datum prometa', 'Prethodno stanje', 'Promet u korist', 'Promet na teret', 'Novo stanje', 'Id racuna'],
  atributi: ['id', 'datumPrometa', 'prethodnoStanje', 'prometUKorist', 'prometNaTeret', 'novoStanje', 'racunId'],
  naslov: 'Dnevna stanja',
  podaciUrl: environment.hostUrl + '/api/dnevnaStanjaRacuna',
  prikazForme: [
  ],
  modelForme: {
  },
  preci: [
    { labela: 'Racun', ruta: '/racuni' }
  ],
  potomci: [
    { labela: 'Analitike izvoda', ruta: '/analitikaIzvoda' }
  ],
  mozeUnos: false
};

const MedjubankarskiNalog: TableInfo = {
  kolone: ['Id naloga',
    'Ukupan iznos',
    'Id valute',
    'Datum valute',
    'Datum',
    'Tip naloga',
    'Id racuna banke duznika', 'Id racuna banke poverioca'],
  atributi: ['id', 'ukupanIznos', 'valutaId', 'datumValute', 'datum', 'tip', 'racunBankeDuznikaId', 'racunBankePoveriocaId'],
  naslov: 'Medjubankarski nalozi',
  podaciUrl: environment.hostUrl + '/api/medjubankarskiNalozi',
  prikazForme: [
  ],
  modelForme: {
  },
  preci: [
    { labela: 'Racun', ruta: '/racuni' },
    { labela: 'Valuta', ruta: '/valute' }
  ],
  mozeUnos: false,
  export: (element: any) => `${environment.hostUrl}/api/medjubankarskiNalozi/${element.id}`
};

const AnalitikaIzvoda: TableInfo = {
  kolone: ['Id analitike izvoda',
          'Duznik/Nalogodavac',
          'Svrha placanja',
          'Poverilac/Primalac',
          'Datum prijema',
          'Datum valute',
          'Racun duznika',
          'Model zaduzenja',
          'Poziv na broj zaduzenja',
          'Racun poverioca',
          'Model odobrenja',
          'Poziv na broj odobrenja',
          'Hitno',
          'Iznos',
          'Tip greske',
          'Status',
          'Id valute',
          'Id naseljenog mesta',
          'Id vrste placanja',
          'Id dnevnog stanja'
        ],
  atributi: [
              'id',
              'duznikNalogodavac',
              'svrhaPlacanja',
              'poverilacPrimalac',
              'datumPrijema',
              'datumValute',
              'racunDuznika',
              'modelZaduzenja',
              'pozivNaBrojZaduzenja',
              'racunPoverioca',
              'modelOdobrenja',
              'pozivNaBrojOdobrenja',
              'hitno',
              'iznos',
              'tipGreske',
              'status',
              'valutaId',
              'naseljenoMestoId',
              'vrstaPlacanjaId',
              'dnevnoStanjeId'
            ],
  naslov: 'Analitike izvoda',
  podaciUrl: environment.hostUrl + '/api/analitika',
  prikazForme: [
    { labela: 'Duznik/Nalogodavac', tip: 'text', imeKontrole: 'duznikNalogodavac' },
    { labela: 'Svrha placanja', tip: 'text', imeKontrole: 'svrhaPlacanja' },
    { labela: 'Poverilac/Primalac', tip: 'text', imeKontrole: 'poverilacPrimalac' },
    { labela: 'Racun duznika', tip: 'text', imeKontrole: 'racunDuznika', table: Racun, roditeljskaKolona: 'brojRacuna' },
    { labela: 'Model zaduzenja', tip: 'number', imeKontrole: 'modelZaduzenja' },
    { labela: 'Poziv na broj zaduzenja', tip: 'text', imeKontrole: 'pozivNaBrojZaduzenja' },
    { labela: 'Racun poverioca', tip: 'text', imeKontrole: 'racunPoverioca', table: Racun, roditeljskaKolona: 'brojRacuna' },
    { labela: 'Model odobrenja', tip: 'number', imeKontrole: 'modelOdobrenja' },
    { labela: 'Poziv na broj odobrenja', tip: 'text', imeKontrole: 'pozivNaBrojOdobrenja' },
    { labela: 'Hitno', tip: 'checkbox', imeKontrole: 'hitno' },
    { labela: 'Iznos', tip: 'number', imeKontrole: 'iznos' },
    { labela: 'Valuta', tip: 'number', imeKontrole: 'valutaId', table: Valuta, roditeljskaKolona: 'id' },
    { labela: 'Naseljeno mesto', tip: 'number', imeKontrole: 'naseljenoMestoId', table: NaseljenoMesto, roditeljskaKolona: 'id' },
    { labela: 'Vrsta placanja', tip: 'number', imeKontrole: 'vrstaPlacanjaId', table: VrstaPlacanja, roditeljskaKolona: 'id' }
  ],
  modelForme: {
    duznikNalogodavac: [null, [Validators.required, Validators.maxLength(256)]],
    svrhaPlacanja: [null, [Validators.required, Validators.maxLength(256)]],
    poverilacPrimalac: [null, [Validators.required, Validators.maxLength(256)]],
    racunDuznika: [null, [Validators.minLength(18), Validators.maxLength(18)]],
    modelZaduzenja: [null, Validators.maxLength(2)],
    pozivNaBrojZaduzenja: [null, [Validators.maxLength(20)]],
    racunPoverioca: [null, [Validators.minLength(18), Validators.maxLength(18)]],
    modelOdobrenja: [null, Validators.maxLength(2)],
    pozivNaBrojOdobrenja: [null, Validators.maxLength(20)],
    hitno: [false, Validators.required],
    iznos: [null, [Validators.required, Validators.maxLength(15)]],
    valutaId: [null],
    naseljenoMestoId: [null],
    vrstaPlacanjaId: [null]
  },
  preci: [
    { labela: 'Dnevno stanje racuna', ruta: '/dnevnoStanjeRacuna' },
    { labela: 'Vrsta placanja', ruta: '/vrstePlacanja' },
    { labela: 'Valuta', ruta: '/valute' },
    { labela: 'Naseljeno mesto', ruta: '/naseljenaMesta' },
  ],
  mozeUnos: true,
  export: (element: any) => `${environment.hostUrl}/api/analitika/${element.id}`,
  import: () => `${environment.hostUrl}/api/analitika/xml`
};

const Ukidanje: TableInfo = {
  kolone: ['Id ukidanja',
    'Datum ukidanja', 'Sredstva prenesena na racun', 'Id ukinutog racuna'],
  atributi: ['id', 'datumUkidanja', 'sredstvaSePrenoseNaRacun', 'racunId'],
  naslov: 'Ukidanja',
  podaciUrl: environment.hostUrl + '/api/ukidanje',
  prikazForme: [
    { labela: 'Id racuna za ukidanje', tip: 'text', imeKontrole: 'racunId', table: Racun, roditeljskaKolona: 'id' },
    { labela: 'Racun na koji se prenose sredstva', tip: 'text', imeKontrole: 'sredstvaSePrenoseNaRacun', table: Racun, roditeljskaKolona: 'brojRacuna' }
  ],
  modelForme: {
    racunId: [null, Validators.required],
    sredstvaSePrenoseNaRacun: [null, Validators.required]
  },
  preci: [
    { labela: 'Racun', ruta: '/racuni' },
  ],
  mozeUnos: true,
}

export const TableRoutes: TableRoute[] = [
  { ruta: 'drzave', labela: 'Drzave', data: Drzava },
  { ruta: 'valute', labela: 'Valute', data: Valuta },
  { ruta: 'naseljenaMesta', labela: 'Naseljena mesta', data: NaseljenoMesto },
  { ruta: 'atributiBanaka', labela: 'Atributi banaka', data: AtributiBanke },
  { ruta: 'fizickaLica', labela: 'Fizicka Lica', data: FizickoLice },
  { ruta: 'pravnaLica', labela: 'Pravna Lica', data: PravnoLice },
  { ruta: 'banke', labela: 'Banke', data: Banka },
  { ruta: 'racuni', labela: 'Racuni', data: Racun },
  { ruta: 'kursnaLista', labela: 'Kursne liste', data: KursnaLista },
  { ruta: 'kursevi', labela: 'Kursevi u valuti', data: KursUValuti },
  { ruta: 'vrstePlacanja', labela: 'Vrste Placanja', data: VrstaPlacanja},
  { ruta: 'dnevnoStanjeRacuna', labela: 'Dnevno Stanje Racuna', data: DnevnoStanjeRacuna},
  { ruta: 'medjubankarskiNalog', labela: 'Medjubankarski Nalozi', data: MedjubankarskiNalog},
  { ruta: 'analitikaIzvoda', labela: 'Analitika izvoda', data: AnalitikaIzvoda},
  { ruta: 'ukidanje', labela: 'Ukidanje', data: Ukidanje}
];
