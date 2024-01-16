import dayjs from 'dayjs/esm';

import { IMaterial, NewMaterial } from './material.model';

export const sampleWithRequiredData: IMaterial = {
  id: 27613,
  name: 'blême sans que',
  type: 'clac',
};

export const sampleWithPartialData: IMaterial = {
  id: 1476,
  name: 'même si',
  type: 'lectorat',
  warrantyEndDate: dayjs('2024-01-15'),
  manufacturer: 'snif',
  model: 'adversaire en vérité',
  serialNumber: 4413,
};

export const sampleWithFullData: IMaterial = {
  id: 7849,
  name: 'occuper assurément',
  type: 'cependant snif tsoin-tsoin',
  purchaseDate: dayjs('2024-01-15'),
  warrantyEndDate: dayjs('2024-01-15'),
  manufacturer: 'quasi',
  model: 'décourager tracer',
  statusMaterial: 'spécialiste adepte peu',
  lastMaintenanceDate: dayjs('2024-01-16'),
  note: 'en guise de',
  serialNumber: 11551,
};

export const sampleWithNewData: NewMaterial = {
  name: 'brusque plier commis',
  type: 'tout à fait détailler spécialiste',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
