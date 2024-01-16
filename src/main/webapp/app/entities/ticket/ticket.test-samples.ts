import dayjs from 'dayjs/esm';

import { ITicket, NewTicket } from './ticket.model';

export const sampleWithRequiredData: ITicket = {
  id: 32688,
  title: 'nettoyer étrangler',
  description: 'tellement équipe de recherche de peur que',
  createdAt: dayjs('2024-01-15'),
};

export const sampleWithPartialData: ITicket = {
  id: 4987,
  title: 'au cas où',
  description: "sale à l'exception de",
  createdAt: dayjs('2024-01-15'),
  resolutionDate: dayjs('2024-01-16'),
  closedAt: dayjs('2024-01-15'),
  limitDate: dayjs('2024-01-16'),
  impact: 'patientèle',
  resolution: 'zzzz',
  attachments: 'comme',
};

export const sampleWithFullData: ITicket = {
  id: 27728,
  title: 'ensuite maigre tant',
  description: 'du fait que',
  createdAt: dayjs('2024-01-15'),
  resolutionDate: dayjs('2024-01-15'),
  closedAt: dayjs('2024-01-16'),
  limitDate: dayjs('2024-01-15'),
  impact: 'lâcher',
  resolution: 'sur',
  attachments: 'triangulaire',
};

export const sampleWithNewData: NewTicket = {
  title: 'mince par rapport à',
  description: 'déterminer',
  createdAt: dayjs('2024-01-16'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
