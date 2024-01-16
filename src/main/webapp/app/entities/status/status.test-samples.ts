import { IStatus, NewStatus } from './status.model';

export const sampleWithRequiredData: IStatus = {
  id: 31205,
  name: 'meuh à force de organiser',
  statusCode: 'dans aigre',
};

export const sampleWithPartialData: IStatus = {
  id: 25237,
  name: 'loin de téméraire meuh',
  statusCode: 'à force de fort',
  colorCode: 'jouer c',
};

export const sampleWithFullData: IStatus = {
  id: 24946,
  name: 'tandis que',
  statusCode: 'corriger au lieu de ',
  description: 'turquoise au point que',
  colorCode: 'dîner a',
  nextPossibleStatus: 'lutter',
  isFinal: false,
};

export const sampleWithNewData: NewStatus = {
  name: 'plutôt trop',
  statusCode: 'immense dessus',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
