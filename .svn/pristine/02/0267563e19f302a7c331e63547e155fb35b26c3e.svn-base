package com.brb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.product.dao.ProductDao;
import com.brb.product.model.MdVo;
import com.brb.product.model.FwrVo;
import com.brb.product.model.PprtVo;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	@Override
	public BrbMap<Object, Object> getModelList(BrbMap<Object, Object> bMap) throws DataAccessException {
		BrbMap<Object, Object> rMap = new BrbMap<>();
		rMap.put("modelList", productDao.getModelList(bMap));
		rMap.put("totalPage", productDao.getModelTotal(bMap));
		return rMap;
	}

	@Override
	public BrbMap<Object, Object> getModelList2(BrbMap<Object, Object> bMap) {
		BrbMap<Object, Object> rMap = new BrbMap<>();
		rMap.put("modelList", productDao.getModelList2(bMap));
		rMap.put("totalPage", productDao.getModelTotal2(bMap));
		return rMap;
	}

	@Override
	public BrbMap<Object, Object> getModelDet(int mdSq) throws DataAccessException {
		return productDao.getModelDet(mdSq);
	}

	@Override
	public void insertModel(MdVo mvo) {
		productDao.insertModel(mvo);
	}

	@Override
	public void updateModel(MdVo mvo) {
		productDao.updateModel(mvo);
	}

	@Override
	public void deleteModel(MdVo mvo) {
		productDao.deleteModel(mvo);
	}

	@Override
	public List<String> getModel() {
		return productDao.getModel();
	}

	@Override
	public BrbMap<Object, Object> getProdList(BrbMap<Object, Object> bMap) throws DataAccessException {
		BrbMap<Object, Object> rMap = new BrbMap<>();
		rMap.put("prodList", productDao.getProdList(bMap));
		rMap.put("totalPage", productDao.getProdTotal(bMap));
		return rMap;
	}

	@Override
	public List<BrbMap<Object, Object>> getProdSearch() {
		return productDao.getProdSearch();
	}

	@Override
	public List<BrbMap<Object, Object>> getCode() throws DataAccessException {
		return productDao.getCode();
	}

	@Override
	public BrbMap<Object, Object> getProdDet(String pprtMac) throws DataAccessException {
		return productDao.getProdDet(pprtMac);
	}

	@Override
	public void matching(PprtVo pvo) {
		productDao.matching(pvo);
	}

	@Override
	public void initProduct(PprtVo pvo) {
		productDao.initProduct(pvo);
	}

	@Override
	public void stopProduct(PprtVo pvo) {
		productDao.stopProduct(pvo);
	}

	@Override
	public BrbMap<Object, Object> getMacList(BrbMap<Object, Object> bMap) {
		BrbMap<Object, Object> rMap = new BrbMap<>();
		rMap.put("macList", productDao.getMacList(bMap));
		rMap.put("totalPage", productDao.getMacTotal(bMap));
		return rMap;
	}

	@Override
	public BrbMap<Object, Object> getMacList2(BrbMap<Object, Object> bMap) {
		BrbMap<Object, Object> rMap = new BrbMap<>();
		rMap.put("macList", productDao.getMacList2(bMap));
		rMap.put("totalPage", productDao.getMacTotal2(bMap));
		return rMap;
	}

	@Override
	public void insertProduct(PprtVo pvo) throws DataAccessException {
		productDao.insertProduct(pvo);
	}

	@Override
	public void insertProduct2(PprtVo pvo) {
		productDao.insertProduct2(pvo);
	}

	@Override
	public List<BrbMap<Object, Object>> fwrList(int mdSq) {
		return productDao.fwrList(mdSq);
	}

	@Override
	public boolean macDupCheck(String pprtMac) {
		return productDao.macDupCheck(pprtMac);
	}

	@Override
	public boolean validation(ArrayList<PprtVo> pvo) {
		return productDao.validation(pvo);
	}

	@Override
	public void updateProduct(MdVo mvo) throws DataAccessException {
		productDao.updateProduct(mvo);
	}

	@Override
	public BrbMap<Object, Object> getFwrList(BrbMap<Object, Object> bMap) throws DataAccessException {
		BrbMap<Object, Object> rMap = new BrbMap<>();
		rMap.put("fwrList", productDao.getFwrList(bMap));
		rMap.put("totalPage", productDao.getFwrTotal(bMap));
		return rMap;
	}

	@Override
	public BrbMap<Object, Object> getFwrDet(int fwrSq) {
		return productDao.getFwrDet(fwrSq);
	}

	@Override
	public void insertFwr(FwrVo fvo) {
		String d1 = fvo.getStartDate();
		String d2 = fvo.getHour();
		String d3 = fvo.getMin();
		if(d2.length() == 1){
			d2 = "0"+d2;
		}
		if(d3.length() == 1){
			d3 = "0"+d3;
		}
		fvo.setAplDttm(d1+" "+d2+":"+d3+":"+"00");
		productDao.insertFwr(fvo);
	}

	@Override
	public void updateFwr(FwrVo fvo) {
		String d1 = fvo.getStartDate();
		String d2 = fvo.getHour();
		String d3 = fvo.getMin();
		if(d2.length() == 1){
			d2 = "0"+d2;
		}
		if(d3.length() == 1){
			d3 = "0"+d3;
		}
		fvo.setAplDttm(d1+" "+d2+":"+d3+":"+"00");
		productDao.updateFwr(fvo);
	}

	@Override
	public void deleteFwr(FwrVo fvo) {
		productDao.deleteFwr(fvo);
	}

	@Override
	public List<BrbMap<Object, Object>> getMem() {
		return productDao.getMem();
	}

	@Override
	public void updateRegMem(PprtVo pvo) {
		productDao.updateRegMem(pvo);
	}

	/*public String xlsExcelReader(MultipartHttpServletRequest req, String[] oriName) {
		List<MdVo> list = new ArrayList<>();
		MultipartFile file = req.getFile("excel");
		HSSFWorkbook workbook = null;
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		try {
			workbook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			MdVo vo;
			String temp = "";
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				curSheet = workbook.getSheetAt(sheetIndex);
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						vo = new MdVo();
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);
									if (curCell != null) {
										if (cellIndex == 0) {
											vo.setMdSq((int) curCell.getNumericCellValue());
										} else if (cellIndex == 1) {
											vo.setCpNm(curCell.getStringCellValue());
										} else if (cellIndex == 2) {
											vo.setDes(curCell.getStringCellValue());
										} else if (cellIndex == 3) {
											vo.setDetSpec(curCell.getStringCellValue());
										} else if (cellIndex == 4) {
											vo.setOrgImageNm(curCell.getStringCellValue());
											for (int i = 0; i < oriName.length; i++) {
												if (oriName[i].equals(curCell.getStringCellValue())) {
													temp = oriName[i];
												}
											}
										} else if (cellIndex == 5) {
											if (temp != "") {
												String[] temp2 = temp.split("\\.");
												temp = temp2[0] + today + "." + temp2[1];
												vo.setChImageNm(temp);
												temp = "";
											} else {
												vo.setChImageNm(curCell.getStringCellValue());
											}
										} else if (cellIndex == 6) {
											vo.setOrgThumNm(curCell.getStringCellValue());
											for (int i = 0; i < oriName.length; i++) {
												if (oriName[i].equals(curCell.getStringCellValue())) {
													temp = oriName[i];
												}
											}
										} else if (cellIndex == 7) {
											if (temp != "") {
												String[] temp2 = temp.split("\\.");
												temp = temp2[0] + today + "." + temp2[1];
												vo.setChThumNm(temp);
												temp = "";
											} else {
												vo.setChThumNm(curCell.getStringCellValue());
											}
										} else if (cellIndex == 8) {
											vo.setInvNum((int) curCell.getNumericCellValue());
										} else if (cellIndex == 9) {
											vo.setStat(curCell.getStringCellValue());
										} else if (cellIndex == 10) {
											vo.setRegDttm(curCell.getStringCellValue());
										} else if (cellIndex == 11) {
											if (curCell.getStringCellValue() != null
													&& curCell.getStringCellValue() != "") {
												vo.setUptDttm(curCell.getStringCellValue());
											}
										} else if (cellIndex == 12) {
											if (curCell.getStringCellValue() != null
													&& curCell.getStringCellValue() != "") {
												vo.setDelDttm(curCell.getStringCellValue());
											}
										} else if (cellIndex == 13) {
											vo.setRegAdmSq((int) curCell.getNumericCellValue());
										} else if (cellIndex == 14) {
											vo.setUptAdmSq((int) curCell.getNumericCellValue());
										} else if (cellIndex == 15) {
											vo.setDelAdmSq((int) curCell.getNumericCellValue());
										} else {
											break;
										}
									}
								} // end for
								list.add(vo);
							} // end
						} // end if
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		productDao.insertExcel(list);
		return today;
	}

	public String xlsxExcelReader(MultipartHttpServletRequest req, String[] oriName) {
		List<MdVo> list = new ArrayList<>();

		MultipartFile file = req.getFile("excel");
		XSSFWorkbook workbook = null;
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;
			MdVo vo;
			int cellCount = 0;
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				curSheet = workbook.getSheetAt(sheetIndex);
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					if (rowIndex == 0) {
						cellCount = curSheet.getRow(rowIndex).getPhysicalNumberOfCells();
					}
					if (rowIndex != 0) {
						String temp = "";
						curRow = curSheet.getRow(rowIndex);
						vo = new MdVo();
						if (curRow.getCell(0) != null) {
							for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
								curCell = curRow.getCell(cellIndex);
								if (curCell != null) {
									if (cellIndex == 0) {
										vo.setMdSq((int) curCell.getNumericCellValue());
									} else if (cellIndex == 1) {
										vo.setCpNm(curCell.getStringCellValue());
									} else if (cellIndex == 2) {
										vo.setDes(curCell.getStringCellValue());
									} else if (cellIndex == 3) {
										vo.setDetSpec(curCell.getStringCellValue());
									} else if (cellIndex == 4) {
										vo.setOrgImageNm(curCell.getStringCellValue());
										for (int i = 0; i < oriName.length; i++) {
											if (oriName[i].equals(curCell.getStringCellValue())) {
												temp = oriName[i];
											}
										}
									} else if (cellIndex == 5) {
										if (temp != "") {
											String[] temp2 = temp.split("\\.");
											temp = temp2[0] + today + "." + temp2[1];
											vo.setChImageNm(temp);
											temp = "";
										} else {
											vo.setChImageNm(curCell.getStringCellValue());
										}
									} else if (cellIndex == 6) {
										vo.setOrgThumNm(curCell.getStringCellValue());
										for (int i = 0; i < oriName.length; i++) {
											if (oriName[i].equals(curCell.getStringCellValue())) {
												temp = oriName[i];
											}
										}
									} else if (cellIndex == 7) {
										if (temp != "") {
											String[] temp2 = temp.split("\\.");
											temp = temp2[0] + today + "." + temp2[1];
											vo.setChThumNm(temp);
											temp = "";
										} else {
											vo.setChThumNm(curCell.getStringCellValue());
										}
									} else if (cellIndex == 8) {
										vo.setInvNum((int) curCell.getNumericCellValue());
									} else if (cellIndex == 9) {
										vo.setStat(curCell.getStringCellValue());
									} else if (cellIndex == 10) {
										vo.setRegDttm(curCell.getStringCellValue());
									} else if (cellIndex == 11) {
										if (curCell.getStringCellValue() != null
												&& curCell.getStringCellValue() != "") {
											vo.setUptDttm(curCell.getStringCellValue());
										}
									} else if (cellIndex == 12) {
										if (curCell.getStringCellValue() != null
												&& curCell.getStringCellValue() != "") {
											vo.setDelDttm(curCell.getStringCellValue());
										}
									} else if (cellIndex == 13) {
										vo.setRegAdmSq((int) curCell.getNumericCellValue());
									} else if (cellIndex == 14) {
										vo.setUptAdmSq((int) curCell.getNumericCellValue());
									} else if (cellIndex == 15) {
										vo.setDelAdmSq((int) curCell.getNumericCellValue());
									} else {
										break;
									}
								}
							}
							list.add(vo);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		productDao.insertExcel(list);
		return today;
	}*/


}