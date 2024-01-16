import dayjs from 'dayjs/esm';

import { IIntervention, NewIntervention } from './intervention.model';

export const sampleWithRequiredData: IIntervention = {
  id: 16987,
  createdBy: 'gratis franco',
  createdAt: dayjs('2024-01-15'),
};

export const sampleWithPartialData: IIntervention = {
  id: 13107,
  description: 'hôte rudement',
  createdBy: 'direction',
  createdAt: dayjs('2024-01-16'),
  attachments: 'd’autant que après que',
};

export const sampleWithFullData: IIntervention = {
  id: 24288,
  title: 'sitôt que',
  description: 'sur apte groin groin',
  createdBy: 'jadis loin de',
  createdAt: dayjs('2024-01-15'),
  attachments: 'entièrement',
  notes: 'depuis',
};

export const sampleWithNewData: NewIntervention = {
  createdBy: 'maigre triste',
  createdAt: dayjs('2024-01-16'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
