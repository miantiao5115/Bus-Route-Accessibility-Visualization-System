package online.miantiao.Entity;

/**
 * �ɴ��Ի����࣬������ͨ��������FID����֮��Ӧ�Ĺ�����·�ɴ���Accessibility
 * 
 * @author ����
 *
 */
public class TAZAccessibilityInfo {
	
	//��ͨ������������ID
	private int fid;
	
	//��ͨ�������Ŀɴ���
	private int accessibility;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public double getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(int accessibility) {
		this.accessibility = accessibility;
	}

	@Override
	public String toString() {
		return "TAZInfo [fid=" + fid + ", accessibility=" + accessibility + "]";
	}

}
