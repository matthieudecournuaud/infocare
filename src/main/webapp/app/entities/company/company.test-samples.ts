import { ICompany, NewCompany } from './company.model';

export const sampleWithRequiredData: ICompany = {
  id: 16879,
  name: 'oh malgré consoler',
  phone: '0698251477',
  siret: 14,
  address: 'âcre dynamique',
};

export const sampleWithPartialData: ICompany = {
  id: 9913,
  name: 'épouser tranquille antagoniste',
  phone: '0645016830',
  siret: 14,
  address: 'au défaut de dans',
  email: 'Bartimee70@gmail.com',
  contactPersonPhone: 'quoique de peur',
  contactPersonEmail: 'au point que hystérique',
  notes: 'hi dring de peur que',
};

export const sampleWithFullData: ICompany = {
  id: 21945,
  name: 'broum fourbe',
  phone: '+33 673695903',
  siret: 14,
  address: 'animer',
  email: 'Leon62@gmail.com',
  contactPerson: 'infime à condition que',
  contactPersonPhone: 'partenaire',
  contactPersonEmail: 'rectangulaire au-dedans de',
  size: 'timide autant séculaire',
  notes: 'collègue',
};

export const sampleWithNewData: NewCompany = {
  name: 'bien que assez gai',
  phone: '+33 642025506',
  siret: 14,
  address: 'conseil d’administration cultiver soulager',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
