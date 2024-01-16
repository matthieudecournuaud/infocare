import { IPriority, NewPriority } from './priority.model';

export const sampleWithRequiredData: IPriority = {
  id: 20869,
  name: 'racheter de sorte que',
};

export const sampleWithPartialData: IPriority = {
  id: 20722,
  name: 'gratis de la part de aux environs de',
  colorCode: 'loufoqu',
};

export const sampleWithFullData: IPriority = {
  id: 29917,
  name: 'désormais triste beaucoup',
  description: 'sombre',
  colorCode: 'crever ',
};

export const sampleWithNewData: NewPriority = {
  name: 'grâce à autour de',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
