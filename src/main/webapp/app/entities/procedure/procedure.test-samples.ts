import dayjs from 'dayjs/esm';

import { IProcedure, NewProcedure } from './procedure.model';

export const sampleWithRequiredData: IProcedure = {
  id: 26468,
};

export const sampleWithPartialData: IProcedure = {
  id: 25134,
  description: 'commis de cuisine',
  category: 'caresser en dépit de à peine',
  estimatedTime: 27574,
  lastReviewed: dayjs('2024-01-16'),
  reviewedBy: 'hystérique',
};

export const sampleWithFullData: IProcedure = {
  id: 11653,
  name: 'restreindre volontiers',
  description: 'inventer sous',
  category: 'brave du côté de',
  procedureId: 28466,
  stepByStepGuide: 'étant donné que vétuste de',
  estimatedTime: 5049,
  requiredTools: 'juriste gens',
  skillsRequired: 'éliminer',
  safetyInstructions: 'hors de bzzz quoique',
  lastReviewed: dayjs('2024-01-16'),
  reviewedBy: 'grrr',
  attachments: 'souple même si oh',
};

export const sampleWithNewData: NewProcedure = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
