import { ICategory, NewCategory } from './category.model';

export const sampleWithRequiredData: ICategory = {
  id: 13439,
  name: 'orange tant que',
};

export const sampleWithPartialData: ICategory = {
  id: 11955,
  name: 'maigre',
};

export const sampleWithFullData: ICategory = {
  id: 16132,
  name: 'incalculable à la merci affable',
  description: 'vorace bondir clac',
  icon: 'moyennant',
};

export const sampleWithNewData: NewCategory = {
  name: 'antique d’autant que membre à vie',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
