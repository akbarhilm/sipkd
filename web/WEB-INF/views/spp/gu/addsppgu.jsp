<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP GU</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah  SPP GU</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppgu"  id="refsppgu" onsubmit="return cek()"  action="${pageContext.request.contextPath}/sppgu/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP GU</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="col-md-7">
                <div class="form-group">
                    <label class="col-md-4 control-label">Tahun Anggaran:</label>
                    <div class="col-md-7">
                        <form:hidden path="id" id='id'    />
                        <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Bulan:</label>
                    <div class="col-md-7">
                        <form:input   path="bulan" id="bulan"  onchange="seturaian()" cssClass="required entrybulan ruleCekBulan" size="8" />mm/yyyy
                        <form:errors path="bulan" class="label label-important" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">SKPD:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                            <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="75S" maxlength="" readonly='true' value=" ${skpd.kodeSkpd}/${skpd.namaSkpd}"    /> 
                            <c:if  test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <form:errors path="skpd.idSkpd" class="label label-important" />
                        </div>
                    </div>
                </div>           
                <div class="form-group">
                    <label class="col-md-4 control-label">No SPP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  /> 
                        </div>
                    </div>
                </div>      
                <div class="form-group">
                    <label class="col-md-4 control-label">Pagu UP (1):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="totalAngaran" id="totalAngaran" cssClass="required  "    size="25"   readonly='true'  /> 
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Total SPP UP/GU (2):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="totalSpp" id="totalSpp" value="${totalSpp}" cssClass="required "    size="25"   readonly='true'  /> 
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Total SPJ (3):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="totalSpj" id="totalSpj" value="${totalSpj}" cssClass="required  "    size="25"   readonly='true'  /> 
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Sisa Uang Saat Ini (2-3):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="nilaiSisaPaguSpp" id="nilaiSisaPaguSpp" value="${nilaiSisaPaguSpp}"  size="25"   readonly='true'  /> 
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Persentase Sisa Uang ((2-3)/1):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="presentase" id="presentase" value="${presentase}" cssClass="required "    size="7"   readonly='true'  /> %
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">NIP Bendahara:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="nipPptk" id="nipPptk"  cssClass="required" readonly="true"  size="30" number='true' maxlength='18'  minlength='16'  />  
                            <form:errors path="nipPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bendahara:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="namaPptk" id="namaPptk"  cssClass="required"  readonly="true" size="45" maxlength="50"   />  
                            <form:errors path="namaPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kode Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="kodeBank" id='kodeBank'    />
                            <form:hidden path="idBank" id='idBank'    />
                            <form:input   path="kodeBankTransfer" id="kodeBankTransfer"  cssClass="required"   size="10" maxlength="7"  readonly="true"  />  
                            <form:errors path="kodeBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="namaBankTransfer" id="namaBankTransfer"  cssClass="required"   size="60" maxlength="75" readonly="true"   />  
                            <form:errors path="namaBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nomor Rekening Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <!--ruleCekCIF -->
                            <form:input   path="nomorRekBank" id="nomorRekBank"  cssClass="required "   size="31" maxlength="30" readonly="true"   />  
                            <form:errors path="nomorRekBank"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Uraian:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:textarea cols="70" rows="3"   path="keterangan" id="uraian"   readonly="true" cssClass="required"      />  
                            <form:errors path="keterangan"  cssClass="error"  />
                        </div>
                    </div>
                </div>  

            </div>
            <div class="col-md-4 col-md-offset-1">

                <div class="portlet box red">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data Bank DKI</div>       

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Kode Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkikodebank" id="dkikodebank" type="text" readonly="true" />
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkinamabank" id="dkinamabank" type="text" readonly="true" size="31" />

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">No Rekening :</label>
                                <div class="col-md-5">
                                    <input name="dkinorek" id="dkinorek" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkinama" id="dkinama" cols="30" ROWS="3" readonly="true"></TEXTAREA>
                                    <!-- <input name="dkinama" id="dkinama" type="text" readonly="true" size="31" /> -->
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>
        </div>
    </div>
    <div class="portlet">
        <div class="portlet-title"> 
            <div class="caption"  ><i class="icon-cogs"></i>Rincian SPP GU</div>       
        </div> 
        <input type="hidden" id="banyakrinci" name="banyakrinci"  />
        <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Nomor SPD</th>
                    <th>Tanggal SPD</th>                    
                    <th>Nilai SPD + Setoran TU</th> 
                    <th>Nilai SPP Sebelum</th> 
                    <th>Nilai Kontrak </th> 
                    <th>Sisa SPD</th> 
                    <th>Nilai SPP</th>                                           
                </tr>               
            </thead>
            <tfoot>
                <tr>
                    <th colspan="6" style="text-align:right">Total:</th>
                    <th>
                        <input type='text' id="totalspd"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th>
                    <th>
                        <input type='text' id="totalspp"   name="totalspp" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th>

                </tr>
            </tfoot>
            <tbody id="spprincitablebody" >
            </tbody>             
        </table> 
    </div> 

</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppgu/addsppgu.js"></script>  
