import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CommissionReportService } from 'app/entities/commission-report/commission-report.service';
import { ICommissionReport, CommissionReport } from 'app/shared/model/commission-report.model';

describe('Service Tests', () => {
  describe('CommissionReport Service', () => {
    let injector: TestBed;
    let service: CommissionReportService;
    let httpMock: HttpTestingController;
    let elemDefault: ICommissionReport;
    let expectedResult: ICommissionReport | ICommissionReport[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CommissionReportService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CommissionReport(0, currentDate, 'AAAAAAA', currentDate, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            cutOffDate: currentDate.format(DATE_FORMAT),
            created: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CommissionReport', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            cutOffDate: currentDate.format(DATE_FORMAT),
            created: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cutOffDate: currentDate,
            created: currentDate,
          },
          returnedFromService
        );

        service.create(new CommissionReport()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CommissionReport', () => {
        const returnedFromService = Object.assign(
          {
            cutOffDate: currentDate.format(DATE_FORMAT),
            additionalComment: 'BBBBBB',
            created: currentDate.format(DATE_FORMAT),
            workOrders: 1,
            totalAmount: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cutOffDate: currentDate,
            created: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CommissionReport', () => {
        const returnedFromService = Object.assign(
          {
            cutOffDate: currentDate.format(DATE_FORMAT),
            additionalComment: 'BBBBBB',
            created: currentDate.format(DATE_FORMAT),
            workOrders: 1,
            totalAmount: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cutOffDate: currentDate,
            created: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CommissionReport', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
